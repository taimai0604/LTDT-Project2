package com.example.taimai.studentmanagement.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.taimai.studentmanagement.DetailStudentActivity;
import com.example.taimai.studentmanagement.EditStudentActivity;
import com.example.taimai.studentmanagement.R;
import com.example.taimai.studentmanagement.fragment.DetailFragment;
import com.example.taimai.studentmanagement.fragment.ListFragment;
import com.example.taimai.studentmanagement.model.Student;
import com.example.taimai.studentmanagement.realm.RealmTM;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

import static android.content.ContentValues.TAG;

/**
 * Created by TaiMai on 11/24/2017.
 */

public class CustomListAdapter extends ArrayAdapter {
    private Context context;
    private List<Student> dataSet;

    private FragmentManager fragmentManager;

    public CustomListAdapter(@NonNull Context context, List<Student> data) {
        super(context, R.layout.item_list, data);
        this.context = context;
        this.dataSet = data;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView tvName;
        ImageView imgEdit;
        ImageView imgDelete;
    }

//    @Override
//    public void onClick(View v) {
//
//        Log.d(TAG, "onClick: " + v.getTag());
//
////        final int position = (Integer) v.getTag();
////        Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
////        Object object = getItem(position);
////
////        final Student dataModel = (Student) object;
////        switch (v.getId()) {
////            case R.id.imgDeleteDevice:
////                new MaterialDialog.Builder(v.getContext())
////                        .title(v.getResources().getString(R.string.delete))
////                        .content(R.string.log_delete)
////                        .positiveText(R.string.agree)
////                        .negativeText(R.string.disagree)
////                        .onPositive(new MaterialDialog.SingleButtonCallback() {
////                            @Override
////                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                                Toast.makeText(context, " edit " + position, Toast.LENGTH_SHORT).show();
////                            }
////                        })
////                        .show();
////                break;
////            case R.id.imgEditDevice:
////                Toast.makeText(context, " delete " + position, Toast.LENGTH_SHORT).show();
////
////                Intent intent = new Intent(context, EditStudentActivity.class);
////                context.startActivity(intent);
////                break;
////        }
//    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Student dataModel = (Student) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_list, parent, false);
            viewHolder.tvName = convertView.findViewById(R.id.tvName);
            viewHolder.imgEdit = convertView.findViewById(R.id.imgEditDevice);
            viewHolder.imgDelete = convertView.findViewById(R.id.imgDeleteDevice);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(dataModel.getName());

        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(v.getContext())
                        .title(v.getResources().getString(R.string.delete))
                        .content(R.string.log_delete)
                        .positiveText(R.string.agree)
                        .negativeText(R.string.disagree)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                RealmTM.INSTANT.getRealm().executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        Student result = realm.where(Student.class).equalTo("id",dataModel.getId()).findFirst();
                                        result.deleteFromRealm();
                                    }
                                });
//                                try {
//                                    Thread.sleep(1000);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
                                fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                                ListFragment listFragment = (ListFragment) fragmentManager.findFragmentByTag("list");
                                FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
                                fragTransaction.detach(listFragment);
                                fragTransaction.attach(listFragment);
                                fragTransaction.commit();
                            }
                        })
                        .show();
            }
        });
        viewHolder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditStudentActivity.class);
                intent.putExtra("idStudent", dataModel.getId());
                context.startActivity(intent);
            }
        });

        // Return the completed view to render on screen
        convertView.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                int currentOrientation = context.getResources().getConfiguration().orientation;
                if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
                    Intent intent = new Intent(getContext(), DetailStudentActivity.class);
                    intent.putExtra("studentID", String.valueOf(dataModel.getId()));
                    intent.putExtra("name", dataModel.getName());
                    intent.putExtra("code", dataModel.getCode());
                    intent.putExtra("description", dataModel.getDescription());
                    context.startActivity(intent);
                } else if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                    DetailFragment detailFragment = (DetailFragment) fragmentManager.findFragmentByTag("detail");
                    detailFragment.updateStudent(dataModel);
                    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
                    fragTransaction.detach(detailFragment);
                    fragTransaction.attach(detailFragment);
                    fragTransaction.commit();
                }
            }
        });

        return convertView;
    }

}

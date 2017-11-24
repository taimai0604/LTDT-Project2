package com.example.taimai.studentmanagement.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taimai.studentmanagement.R;
import com.example.taimai.studentmanagement.model.Student;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    private TextView tvID;
    private TextView tvName;
    private TextView tvCode;
    private TextView tvDescrition;

    Student student;

    private static final String STATE_COUNTER = "counter";
    private int mCounter;

    public DetailFragment() {
    }

    public void updateStudent(Student student) {
        this.student = student;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // If we have a saved state then we can restore it now
        if (savedInstanceState != null) {
            mCounter = savedInstanceState.getInt(STATE_COUNTER, 0);
        }

        Toast.makeText(getContext(), "" + mCounter, Toast.LENGTH_SHORT).show();
        mCounter++;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        tvID = view.findViewById(R.id.tvID);
        tvCode = view.findViewById(R.id.tvCode);
        tvName = view.findViewById(R.id.tvName);
        tvDescrition = view.findViewById(R.id.tvDescription);

        if (student != null) {
            tvID.setText(student.getId() + "");
            tvCode.setText(student.getCode());
            tvName.setText(student.getName());
            tvDescrition.setText(student.getDescription());
        } else {
            tvID.setText("");
            tvCode.setText("");
            tvName.setText("");
            tvDescrition.setText("");
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_COUNTER, mCounter);
    }
}

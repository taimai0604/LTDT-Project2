package com.example.taimai.studentmanagement;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.taimai.studentmanagement.fragment.DetailFragment;
import com.example.taimai.studentmanagement.fragment.ListFragment;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        int currentOrientation = this.getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
//            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            Toast.makeText(this, "orientation", Toast.LENGTH_SHORT).show();
            ListFragment listFragment = new ListFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(R.id.frgContent, listFragment, "list");
            ft.commit();
        } else if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
//            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            Toast.makeText(this, "Landscape", Toast.LENGTH_SHORT).show();
            ListFragment listFragment = new ListFragment();
            DetailFragment detailFragment = new DetailFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(R.id.frgList, listFragment, "list");
            ft.add(R.id.frgDetail, detailFragment, "detail");
            ft.commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addStudent:
                Intent intent = new Intent(this, AddStudentActivity.class);
                startActivity(intent);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

}

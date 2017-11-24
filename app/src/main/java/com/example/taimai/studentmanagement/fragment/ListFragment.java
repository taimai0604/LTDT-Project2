package com.example.taimai.studentmanagement.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.taimai.studentmanagement.R;
import com.example.taimai.studentmanagement.adapter.CustomListAdapter;
import com.example.taimai.studentmanagement.model.Student;
import com.example.taimai.studentmanagement.realm.RealmTM;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    private ListView lvStudent;
    private List<Student> students;
    private CustomListAdapter adapter;

    private FragmentManager fragmentManager;


    public ListFragment() {
        students = RealmTM.INSTANT.findAll(Student.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        //get list view
        lvStudent = view.findViewById(R.id.lvStudent);
        adapter = new CustomListAdapter(getContext(), students);

        lvStudent.setAdapter(adapter);

        return view;
    }


}

package com.example.taimai.studentmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetailStudentActivity extends AppCompatActivity {
    private TextView tvID;
    private TextView tvName;
    private TextView tvCode;
    private TextView tvDescrition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_student);
        tvID = findViewById(R.id.tvID);
        tvCode = findViewById(R.id.tvCode);
        tvName = findViewById(R.id.tvName);
        tvDescrition = findViewById(R.id.tvDescription);
        tvID.setText(getIntent().getStringExtra("studentID"));
        tvCode.setText(getIntent().getStringExtra("code"));
        tvName.setText(getIntent().getStringExtra("name"));
        tvDescrition.setText(getIntent().getStringExtra("description"));
    }
}

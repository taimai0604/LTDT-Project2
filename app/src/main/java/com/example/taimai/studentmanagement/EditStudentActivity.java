package com.example.taimai.studentmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taimai.studentmanagement.model.Student;
import com.example.taimai.studentmanagement.realm.RealmTM;

public class EditStudentActivity extends AppCompatActivity {

    private EditText edCode;
    private EditText edName;
    private EditText edDescription;

    private Student student;
    private String TAG = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        Log.d(TAG, "onCreate: " + getIntent().getIntExtra("idStudent", -1));

        int idStudent = getIntent().getIntExtra("idStudent", -1);
        if(idStudent == -1){
            finish();
        }

        RealmTM.INSTANT.getRealm().beginTransaction();
        student = RealmTM.INSTANT.getRealm().where(Student.class).equalTo("id", idStudent).findFirst();
        RealmTM.INSTANT.getRealm().commitTransaction();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Edit Student");
        setSupportActionBar(toolbar);

        edCode = findViewById(R.id.edCode);
        edName = findViewById(R.id.edName);
        edDescription = findViewById(R.id.edDescription);

        edCode.setText(student.getCode());
        edName.setText(student.getName());
        edDescription.setText(student.getDescription());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.CommitAddStudent:
                RealmTM.INSTANT.getRealm().beginTransaction();
                student.setName(edName.getText().toString());
                student.setCode(edCode.getText().toString());
                student.setDescription(edDescription.getText().toString());
                RealmTM.INSTANT.getRealm().commitTransaction();

                Toast.makeText(this, "Success!!", Toast.LENGTH_SHORT).show();
                finish();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.taimai.studentmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taimai.studentmanagement.model.Student;
import com.example.taimai.studentmanagement.realm.RealmTM;

import io.realm.Realm;

public class AddStudentActivity extends AppCompatActivity {
    private EditText edCode;
    private EditText edName;
    private EditText edDescription;

    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Add Student");
        setSupportActionBar(toolbar);

        edCode = findViewById(R.id.edCode);
        edName = findViewById(R.id.edName);
        edDescription = findViewById(R.id.edDescription);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.CommitAddStudent:
                student = new Student();
                int id = getNextKey();
                student.setId(id);
                student.setName(edName.getText().toString());
                student.setCode(edCode.getText().toString());
                student.setDescription(edDescription.getText().toString());
                RealmTM.INSTANT.addRealm(student);

                Toast.makeText(this, "Success!!", Toast.LENGTH_SHORT).show();
                finish();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public int getNextKey() {
        try {
            Number number = RealmTM.INSTANT.getRealm().where(Student.class).max("id");
            if (number != null) {
                return number.intValue() + 1;
            } else {
                return 0;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }
}

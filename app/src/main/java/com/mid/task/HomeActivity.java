package com.mid.task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity  implements View.OnClickListener {

    Button viewCourses,myCourses ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewCourses=findViewById(R.id.viewCourses);
        myCourses=findViewById(R.id.myCourses);
        viewCourses.setOnClickListener(this);
        myCourses.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.viewCourses:
                Intent intent  = new Intent(this, ViewCoursesActivity.class);

               this.startActivity(intent);


                break;
            case R.id.myCourses:

                Intent intent1  = new Intent(this, MYCoursesActivity.class);

                this.startActivity(intent1);

                break;
        }
    }
}

package com.fuais.janaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    CheckBox android, java, database, datastru,oop;
    Button add ;
    final HashMap<String, Object> objectHashMap = new HashMap<>();
    List<CheckBox> items = new ArrayList<CheckBox>();
    String txtandroid, txtjava, txtdatabase, txtdatastru,txtoop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        android = (CheckBox) findViewById(R.id.androidCheckBox);
        android.setOnClickListener(this);
        java = (CheckBox) findViewById(R.id.javaCheckBox);
        java.setOnClickListener(this);
        database = (CheckBox) findViewById(R.id.databCheckBox);
        database.setOnClickListener(this);
        datastru = (CheckBox) findViewById(R.id.datasCheckBox);
        datastru.setOnClickListener(this);
        add=findViewById(R.id.add);
        final DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Courses");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                      final   DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("MyCourse");

databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
course course= dataSnapshot.getValue(com.fuais.janaproject.course.class);
        databaseReference.child("java").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    if (android.isChecked()){

                        Toast.makeText(getApplication(), "You must complete the pre-requisite before registering "+android.getText().toString(), Toast.LENGTH_SHORT).show();


                    }
                    else if (datastru.isChecked()){

                        Toast.makeText(getApplication(), "You must complete the pre-requisite before registering "+datastru.getText().toString(), Toast.LENGTH_SHORT).show();

                    }
                    else if (oop.isChecked()){

                        Toast.makeText(getApplication(), "You must complete the pre-requisite before registering "+oop.getText().toString(), Toast.LENGTH_SHORT).show();

                    }
                    else if (java.isChecked()){

                        databaseReference.updateChildren(objectHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getApplication(), "Done", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(getApplication(), "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }

                }
                else {
                    databaseReference.updateChildren(objectHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplication(), "Done", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplication(), "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});



                }

        });
      oop = (CheckBox) findViewById(R.id.oopCheckBox);
        oop.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.androidCheckBox:
                if (android.isChecked()) {
                    objectHashMap.put("android", android.getText().toString());
                    Toast.makeText(getApplicationContext(), "Android", Toast.LENGTH_LONG).show();
                }
                else {
                    objectHashMap.remove("android");
                }
                break;
            case R.id.javaCheckBox:
                if (java.isChecked()){
                    objectHashMap.put("java",java.getText().toString());
                    Toast.makeText(getApplicationContext(), "Java", Toast.LENGTH_LONG).show();}
                else {
                    objectHashMap.remove("java");
                }
                break;
            case R.id.databCheckBox:
                if (database.isChecked()){
                    objectHashMap.put("database" , database.getText().toString());
                    Toast.makeText(getApplicationContext(), "database", Toast.LENGTH_LONG).show();}
                else {
                    objectHashMap.remove("database");
                }
                break;
            case R.id.datasCheckBox:
                if (datastru.isChecked()){
                    objectHashMap.put("datastr", datastru.getText().toString());
                    Toast.makeText(getApplicationContext(), "data structure", Toast.LENGTH_LONG).show();}
                else {
                    objectHashMap.remove("datastr");
                }
                break;
            case R.id.oopCheckBox:
                if (oop.isChecked()){
                    objectHashMap.put("oop", oop.getText().toString());
                    Toast.makeText(getApplicationContext(), "object oriented programming", Toast.LENGTH_LONG).show();
                }
                else {
                    objectHashMap.remove("oop");

                }
                break;
        }
    }
}

package com.mid.task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class CourseInformationActivity extends AppCompatActivity {
    ImageView imageView ;
    TextView name,about,link;
    LinearLayout linearLayout;
    Button add;
    final HashMap<String, Object> objectHashMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_information);
        imageView=findViewById(R.id.img);
        add=findViewById(R.id.add);
        name =findViewById(R.id.name);
        about=findViewById(R.id.about);
        link=findViewById(R.id.link);
        linearLayout=findViewById(R.id.ly);
        objectHashMap.put("image",getIntent().getStringExtra("img"));
        objectHashMap.put("link",getIntent().getStringExtra("link"));
        objectHashMap.put("about",getIntent().getStringExtra("about"));
        objectHashMap.put("name",getIntent().getStringExtra("name"));
        name.setText( getIntent().getStringExtra("name"));
        about.setText( getIntent().getStringExtra("about"));
        link.setText( getIntent().getStringExtra("link"));
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( getIntent().getStringExtra("link")));
                startActivity(browserIntent);
            }
        });

        Picasso.get().load( getIntent().getStringExtra("img"))
                .placeholder(R.drawable.ic_launcher_background).into(imageView);
        if(!getIntent().getBooleanExtra("add",true)){
            add.setText("Remove");
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("myCourse");
                if(getIntent().getBooleanExtra("add",true)){
                databaseReference.child(LoginActivity.userId+"").push().setValue(objectHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                else{
                    databaseReference.child(LoginActivity.userId+"").child(getIntent().getStringExtra("id")).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<Void> task) {
                            Toast.makeText(getApplication(), "Done", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });

                }


            }
        });
    }
}
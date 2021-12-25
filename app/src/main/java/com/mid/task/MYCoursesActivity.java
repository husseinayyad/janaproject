package com.mid.task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MYCoursesActivity extends AppCompatActivity {
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    Context context ;
    private List<String> name=new ArrayList<>();

    private List<String> img=new ArrayList<>();
    private List<String> link=new ArrayList<>();
    private List<String> about=new ArrayList<>();

    private List<String> key=new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    RelativeLayout noData;
    public static  boolean home=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courses);
        recyclerView=findViewById(R.id.recyclerViewCatg);
        home=true;
        noData =findViewById(R.id.nodatafound);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("myCourse");
        mDatabase.child(LoginActivity.userId+"").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    name.clear();
                    img.clear();
                    about.clear();
                    link.clear();


                    for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                        Course course= dataSnapshot1.getValue(Course.class);


                        name.add(course.getName());
                        img.add(course.getImage());
                        about.add(course.getAbout());
                        link.add(course.getLink());
                        key.add(dataSnapshot1.getKey());

                    }

                    adapter = new CourseAdapter( getApplicationContext(),name,img,key,about,link,false);

                    linearLayoutManager = new LinearLayoutManager(getApplicationContext());


                    dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), linearLayoutManager.getOrientation());

                    recyclerView.setHasFixedSize(true);
                    recyclerView .setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                    recyclerView.addItemDecoration(dividerItemDecoration);
                    recyclerView.setAdapter(adapter);
                }
                else {
                    noData.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                noData.setVisibility(View.VISIBLE);
            }
        });
    }
}
package com.example.a3634assignment.Courses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.a3634assignment.HomePage;
import com.example.a3634assignment.R;
import com.example.a3634assignment.Videos.VideoDes;
import com.example.a3634assignment.Videos.VideoPage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CourseList extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.a3634assignment.Courses";

    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Courses> courseList;

    private static final String TAG = "CourseList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        recyclerView = findViewById(R.id.courseTopics);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        courseList = new ArrayList<Courses>();
        recyclerView.setLayoutManager(layoutManager);

        final CourseListAdapter.RecyclerViewClickListener listener = new CourseListAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                launchDetailActivity(position);
            }
        };

        //retrieve data stored under the name "Courses" in FireBase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Courses");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Reads data stored in FireBase, set into adapter
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Courses courses = dataSnapshot1.getValue(Courses.class);
                    courseList.add(courses);
                }

                adapter = new CourseListAdapter(courseList, listener);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // If failed to read value from FireBase, message is displayed
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    private void launchDetailActivity(int position) {

        Intent intent = new Intent(this, VideoDes.class);
        intent.putExtra(EXTRA_MESSAGE, position);
        startActivity(intent);

    }
}

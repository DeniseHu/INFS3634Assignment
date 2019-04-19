package com.example.a3634assignment.Courses;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a3634assignment.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//Reference code for CourseListAdapter & CourseList: https://www.youtube.com/watch?v=vpObpZ5MYSE
public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.AdapterViewHolder> {
    private ArrayList<Courses> mCourses;
    private RecyclerViewClickListener mListener;

    public CourseListAdapter(ArrayList<Courses> courses, RecyclerViewClickListener listener) {
        mCourses = courses;
        mListener = listener;
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public static class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView courseName, shortDes;
        public ImageView courseImage;
        private RecyclerViewClickListener mListener;

        public AdapterViewHolder(View v, RecyclerViewClickListener listener) {

            super(v);
            mListener = listener;
            v.setOnClickListener(this);

            courseName = (TextView) itemView.findViewById(R.id.course_name);
            shortDes = (TextView) itemView.findViewById(R.id.short_des);
            courseImage = (ImageView) itemView.findViewById(R.id.course_image);

        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }


    @Override
    public CourseListAdapter.AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.courselist_row, parent, false);
        return new CourseListAdapter.AdapterViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(CourseListAdapter.AdapterViewHolder adapterViewHolder, int position) {

        adapterViewHolder.courseName.setText(mCourses.get(position).getCourseName());
        adapterViewHolder.shortDes.setText(mCourses.get(position).getCourseDes());
        //To load image from FireBase using Picasso
        Picasso.get().load(mCourses.get(position).getCourseImage()).into(adapterViewHolder.courseImage);
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }
}

package com.example.a3634assignment.Courses;

public class Courses {
    private String courseName;
    private String courseDes;
    private String courseImage;

    public Courses(){

    }

    public Courses(String courseName, String courseDes, String courseImage) {
        this.courseName = courseName;
        this.courseDes = courseDes;
        this.courseImage = courseImage;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDes() {
        return courseDes;
    }

    public void setCourseDes(String courseDes) {
        this.courseDes = courseDes;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }
}

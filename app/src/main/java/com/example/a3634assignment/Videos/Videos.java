package com.example.a3634assignment.Videos;

import java.util.ArrayList;

public class Videos {

    private String videoName;
    private String videoUrl;
    private String contentDes;

    public Videos(String videoName, String videoUrl, String contentDes) {

        this.videoName = videoName;
        this.videoUrl = videoUrl;
        this.contentDes = contentDes;

    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getContentDes() {
        return contentDes;
    }

    public void setContentDes(String contentDes) {
        this.contentDes = contentDes;
    }

    public static ArrayList<Videos> getVideos() {
        ArrayList<Videos> videos = new ArrayList<>();

        videos.add(new Videos("Saving vs Investing", "SoHgDXLj9hY", "Saving money and investing money are not the same thing. This video helps you to understand this fundamental concept, and prepares you for further financial learning."));
        videos.add(new Videos("Labour Market", "mxZrsRMSOis", "A labour market is the place where workers and employees interact with each other. Concepts such as equilibrium, supply and demand  will be introduced in the video."));
        videos.add(new Videos("Fiscal Policy", "4FNdUTN4cHY", "Fiscal policy refers to the use of government spending and tax policies to influence economic conditions.Types of fiscal policies and its corresponding impact are being explained and analysed in the above video."));

        return videos;
    }
}

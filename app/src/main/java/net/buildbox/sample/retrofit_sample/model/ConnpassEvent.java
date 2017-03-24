package net.buildbox.sample.retrofit_sample.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConnpassEvent {
    @SerializedName("results_returned")
    public int results;
    public List<Events> events;

    public static class Events {
        public String event_id;
        public String title;
        @SerializedName("catch")
        public String subTitle;
        public String description;
    }
}

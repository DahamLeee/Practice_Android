package org.techtown.proj4;

public class Review {
    String ID;
    String Time;
    String Content;

    public Review(String ID, String time, String content) {
        this.ID = ID;
        Time = time;
        Content = content;
    }

    public String getID() {
        return ID;
    }

    public String getTime() {
        return Time;
    }

    public String getContent() {
        return Content;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setContent(String content) {
        Content = content;
    }
}

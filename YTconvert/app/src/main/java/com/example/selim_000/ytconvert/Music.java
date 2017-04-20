package com.example.selim_000.ytconvert;

/**
 * Created by selim_000 on 19/04/2017.
 */

public class Music {
    private String title ="";
    private String length = "";
    private String link ="";

    public Music(String title, String length, String link) {
        this.title = title;
        this.length = length;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

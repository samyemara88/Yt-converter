package com.example.selim_000.ytconvert;

/**
 * Created by selim_000 on 19/04/2017.
 */

public class Music {
    private String title ="";
    private int length = 0;
    private String link ="";

    public Music(String title, int length, String link) {
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

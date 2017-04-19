package com.example.selim_000.ytconvert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String URL_MUSIC_SEARCH = "www.youtubeinmp3.com/fetch/?format=JSON&video=http://www.youtube.com/watch?v=i62Zjga8JOM";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadJsonTask jsonTask = new DownloadJsonTask();
        jsonTask.execute(URL_MUSIC_SEARCH);
    }
}

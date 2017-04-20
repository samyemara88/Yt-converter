package com.example.selim_000.ytconvert;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String URL_MUSIC_SEARCH = "//www.youtubeinmp3.com/fetch/?video=https://www.youtube.com/watch?v=i62Zjga8JOM";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadJsonTask jsonTask = new DownloadJsonTask();
        jsonTask.execute(URL_MUSIC_SEARCH);

        setContentView(R.layout.activity_main);

        final Context context = this;

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https:\\/\\/www.youtubeinmp3.com\\/download\\/get\\/?i=Gge7cDalLjlUvrJYjoTqC4O6b2b7s8Hr&e=42"));
                startActivity(intent);
            }
        });
    }
}

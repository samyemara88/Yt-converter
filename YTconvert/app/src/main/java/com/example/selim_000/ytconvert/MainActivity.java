package com.example.selim_000.ytconvert;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private EditText text_url;
    private  final String URL_MUSIC_SEARCH = "https://www.youtubeinmp3.com/fetch/?format=JSON&video=";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        text_url = (EditText)findViewById (R.id.editText);

        final Context context = this;

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DownloadJsonTask jsonTask = new DownloadJsonTask(new IMusiqueListener() {
                    @Override
                    public void IMusiqueListener(Music m) {

                        String url_download =  m.getLink().toString();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url_download));
                        startActivity(intent);

                    }
                });
                String video_url =  text_url.getText().toString() ;
                String last_url = URL_MUSIC_SEARCH + video_url ;
                jsonTask.execute(last_url);
            }
        });
    }
}

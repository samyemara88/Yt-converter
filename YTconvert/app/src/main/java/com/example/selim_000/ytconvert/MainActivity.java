package com.example.selim_000.ytconvert;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private EditText text_url;
    private  final String URL_MUSIC_SEARCH = "https://www.youtubeinmp3.com/fetch/?format=JSON&video=";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission();
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


                        DownloadMp3Task mp3Task = new DownloadMp3Task(m);
                        mp3Task.execute();

                    }
                });
                String video_url =  text_url.getText().toString() ;
                String last_url = URL_MUSIC_SEARCH + video_url ;
                jsonTask.execute(last_url);
            }
        });
    }

    private void checkPermission(){

        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)){

                new AlertDialog.Builder(this).setTitle("Access permission").setMessage("The App needs to access your local storage to download the file")
                        .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.INTERNET}, 1);

                            }
                        }).create().show();
            }ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.INTERNET}, 1);

        }

    }
}

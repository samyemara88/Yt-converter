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
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText text_url;
    private Button buttonDownload;
    private  final String URL_MUSIC_SEARCH = "https://www.youtubeinmp3.com/fetch/?format=JSON&video=";
    @Override

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setContentView(R.layout.activity_main);
            recreate();

        }
        return super.onKeyDown(keyCode, event);

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission();


        setContentView(R.layout.activity_main);
        text_url = (EditText)findViewById (R.id.editText);


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
                Context context = getApplicationContext();
                CharSequence text = "Votre musique est en cour de téléchargement";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        buttonDownload = (Button) findViewById(R.id.button2);
        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.downloads);
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

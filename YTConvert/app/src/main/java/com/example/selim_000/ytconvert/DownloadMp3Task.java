package com.example.selim_000.ytconvert;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by selim_000 on 20/04/2017.
 */

public class DownloadMp3Task extends AsyncTask<String, Void, String>{

    private Music music;

    public DownloadMp3Task(Music music) {
        this.music = music;
    }

    @Override
    protected String doInBackground(String... params) {
         try {
            URL aUrl = new URL( music.getLink() );
            URLConnection conn = aUrl.openConnection();
            int contentLength = conn.getContentLength();

            DataInputStream stream = new DataInputStream(aUrl.openStream());
            byte[] buffer = new byte[contentLength];
            stream.readFully(buffer);
            stream.close();


            DataOutputStream fos;

            File myFile = new File(Environment.getExternalStorageDirectory()+"/Download/"+ music.getTitle()+".mp3");
            fos = new DataOutputStream(new FileOutputStream(myFile));
            fos.write(buffer);
            fos.flush();
            fos.close();

             Log.d("test", "end download");

        } catch (IOException e) {
            Log.e("YTConvert", "Error getting MP3 file : "+e.getMessage().toString());
        }
        return null;
    }
}

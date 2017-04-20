package com.example.selim_000.ytconvert;

import android.os.AsyncTask;
import android.widget.BaseAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by selim_000 on 19/04/2017.
 */

public class DownloadJsonTask extends AsyncTask<String, Void, String> {

    private IMusiqueListener listener ;

    public DownloadJsonTask(IMusiqueListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject jsonObject= new JSONObject(result);
            Music musique = new Music(jsonObject.getString("title"),
                                      jsonObject.getString("length"),
                                      jsonObject.getString("link")) ;
            listener.IMusiqueListener(musique);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        return download_xmldom(params[0]);
    }

    private String download_xmldom(String url){
        String result="";
        try {
            URL aUrl = new URL(url);
            URLConnection conn= aUrl.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            result= convertStreamToString(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String convertStreamToString(InputStream is){
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try{
            while((line = reader.readLine()) != null){
                sb.append(line).append('\n');
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}

package com.christhack.rsinha.christhack.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by rsinha on 3/2/17.
 */

public class MyDataUnit {
    private String text;
    Double positive,  negative,neutral;
    private ArrayList<Emotions> emotions;
    private String TAG = "MyDatabase";
    private Context context;

    public MyDataUnit(String text, Context cont) {
        emotions = new ArrayList<Emotions>();
        this.text = text;
        context = cont;
        getSentiments();
    }

    public void getSentiments() {
        new GetData(true, context).execute();
    }

    public class Emotions {
        String emotions;
        int value;
    }

    public class GetData extends AsyncTask<Void, Void, Void> {
        private String link = "https://api.theysay.io/v1/";
        private Context cont;

        public GetData(boolean sentiment, Context context) {
            Log.i(TAG, "It got in initialisation");
            cont = context;
            if (sentiment)
                link = link + "sentiment";
            else
                link = link + "emotion";
            Log.i(TAG, "It got initialised");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
//                Log.i(TAG, "Backgroud Work");
//                HttpClient httpClient = new DefaultHttpClient();
//                HttpPost httpGet = new HttpPost(link);
//                List<NameValuePair> ln = new ArrayList<NameValuePair>();
//                ln.add(new BasicNameValuePair("text", text));
//                ln.add(new BasicNameValuePair("level", "sentence"));
//                httpGet.setEntity(new UrlEncodedFormEntity(ln));
//                //Log.i("Check", "Connecting...");
//                HttpResponse httpResponse = httpClient.execute(httpGet);
//                //publishProgress("Entity Creating...");
//                //Log.i("Check","Entity Creating..." );
//                HttpEntity httpEntity = httpResponse.getEntity();
//
//                //Log.i("Check","1st Place");
//                String para = EntityUtils.toString(httpEntity);
//                Log.i(TAG, "Data got : "+para);



                //TODO Here Add the code to extract words from the sentence and find the sentiments for local analysis

            } catch (Exception e) {
                Log.i(TAG,"Here 226 "+e.getMessage());
            }
            //Log.i("Check",para);
            //JSONArray jsonArray = new JSONArray(para);
            Log.i(TAG, "Done");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
        //public void extractWords
    }
}

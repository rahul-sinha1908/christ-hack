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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentEmotion;
import com.ibm.watson.developer_cloud.http.ServiceCall;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by rsinha on 3/2/17.
 */

public class MyDataUnit {
    private String text;
    Double positive,  negative,neutral;
    private String TAG = "MyDatabase";
    private Context context;
    private DatabaseReference myDataBase;

    public MyDataUnit(String text, Context cont) {
        this.text = text;
        context = cont;
        myDataBase= FirebaseDatabase.getInstance().getReference();
        myDataBase.setValue("Hello");
        getSentiments();
    }

    public void getSentiments() {
        new FetchData(text).execute();
    }

    class FetchData extends AsyncTask<Void, Void, Void>{
        private String message;
        public FetchData(String mess){
            message=mess;
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                AlchemyLanguage service = new AlchemyLanguage();
                service.setApiKey("0fd9a51cd63514147b56e2a2cad1a0ccb2e97073");
                service.setEndPoint("https://gateway-a.watsonplatform.net/calls");
                //service.setUsernameAndPassword("1655e4b1-ddae-411a-b9ec-77dbf4aa8f6c", "6BqRQ0Jqhdnf");
//                service.setLanguage(LanguageSelection.ENGLISH);
                Map<String, Object> myMap = new HashMap<String, Object>();
                myMap.put("text", new String(message));
                Log.i(TAG, "Its Starting to process : " + message);
                ServiceCall<DocumentEmotion> call = service.getEmotion(myMap);
                if(call !=null)
                    Log.i(TAG, "Its got processed 1");
                DocumentEmotion emotion = call.execute();
                Log.i(TAG, "Its got processed 0");
                DocumentEmotion.Emotion emo = emotion.getEmotion();
                Log.i(TAG, "Its got processed");

                Log.i(TAG, "Anger : " + emo.getAnger());
                Log.i(TAG, "Disgust : " + emo.getDisgust());
                Log.i(TAG, "Fear : " + emo.getFear());
                Log.i(TAG, "Joy : " + emo.getJoy());
                Log.i(TAG, "Sadness : " + emo.getSadness());
                String user=SharedP.getValues(context,"name");
                String college=SharedP.getValues(context, "college");
                myDataBase.child(college).child("data").child("anger").setValue(emo.getAnger());
                myDataBase.child(college).child("data").child("disgust").setValue(emo.getDisgust());
                myDataBase.child(college).child("data").child("fear").setValue(emo.getFear());
                myDataBase.child(college).child("data").child("happy").setValue(emo.getJoy());
                myDataBase.child(college).child("data").child("sad").setValue(emo.getSadness());
            }catch (Exception ex){
                Log.i(TAG, ex.getMessage());
            }
            return null;
        }
    }
}

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
import java.util.Objects;


/**
 * Created by rsinha on 3/2/17.
 */

public class MyDataUnit {
    private String text;
    Double positive,  negative,neutral;
    private String TAG = "MyDatabase";
    private Context context;
    private DatabaseReference myDataBase;
    ValueEventListener eventListener;

    public MyDataUnit(String text, Context cont) {
        this.text = text;
        context = cont;
        myDataBase= FirebaseDatabase.getInstance().getReference();
        String user=SharedP.getValues(context,"name");
        String college=SharedP.getValues(context, "college");
        eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> map = (HashMap<String, Object>)dataSnapshot.getValue();
                Log.i(TAG, map.toString());
                getSentiments(map);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myDataBase.child(college).child("data").addValueEventListener(eventListener);
        //getSentiments();
    }

    public void getSentiments(Map<String, Object> m) {
        new FetchData(text, m).execute();
    }

    class FetchData extends AsyncTask<Void, Void, Void>{
        private String message;
        Map<String, Object> old;
        public FetchData(String mess, Map<String, Object> map){
            message=mess;
            old=map;
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
                double anger = Double.parseDouble(old.get("anger").toString())+emo.getAnger()-SharedP.getAnger(context);
                double fear = Double.parseDouble(old.get("fear").toString())+emo.getFear()-SharedP.getFear(context);
                double happy = Double.parseDouble(old.get("happy").toString())+emo.getJoy()-SharedP.getHappy(context);
                double sad = Double.parseDouble(old.get("sad").toString())+emo.getSadness()-SharedP.getSad(context);
                double disgust = Double.parseDouble(old.get("disgust").toString())+emo.getDisgust()-SharedP.getDisgust(context);

                Map<String, Object> map=new HashMap<String, Object>();
                map.put("anger", anger);
                map.put("fear", fear);
                map.put("happy", happy);
                map.put("sad", sad);
                map.put("disgust", disgust);
                myDataBase.child(college).child("data").removeEventListener(eventListener);
                myDataBase.child(college).child("data").updateChildren(map);

//                myDataBase.child(college).child("data").child("anger").setValue(emo.getAnger());
//                myDataBase.child(college).child("data").child("disgust").setValue(emo.getDisgust());
//                myDataBase.child(college).child("data").child("fear").setValue(emo.getFear());
//                myDataBase.child(college).child("data").child("happy").setValue(emo.getJoy());
//                myDataBase.child(college).child("data").child("sad").setValue(emo.getSadness());

                SharedP.setAnger(context, emo.getAnger());
                SharedP.setFear(context, emo.getFear());
                SharedP.setHappy(context, emo.getJoy());
                SharedP.setSad(context, emo.getSadness());
                SharedP.setDisgust(context, emo.getDisgust());
            }catch (Exception ex){
                Log.i(TAG, ex.getMessage());
            }
            return null;
        }
    }
}


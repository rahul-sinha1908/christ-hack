package com.christhack.rsinha.christhack;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentEmotion;
import com.ibm.watson.developer_cloud.alchemy.v1.model.LanguageSelection;
import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.http.ServiceCallback;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.sample_id);
        button=(Button) findViewById(R.id.process_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FetchData(editText.getText().toString()).execute();
            }
        });
        //service.getEmotion();
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
                service.setEndPoint("https://gateway.watsonplatform.net/natural-language-understanding/api");
                service.setUsernameAndPassword("1655e4b1-ddae-411a-b9ec-77dbf4aa8f6c", "6BqRQ0Jqhdnf");
                service.setLanguage(LanguageSelection.ENGLISH);

                Map<String, Object> myMap = new HashMap<String, Object>();
                myMap.put("text", new String(message));
                Log.i(TAG, "Its Starting to process : " + message);
//                service.getEmotion(myMap).enqueue(new ServiceCallback<DocumentEmotion>() {
//                    @Override
//                    public void onResponse(DocumentEmotion emotion) {
//                        Log.i(TAG, "Its got processed 0");
//                        DocumentEmotion.Emotion emo = emotion.getEmotion();
//                        Log.i(TAG, "Its got processed");
//
//                        Log.i(TAG, "Anger : " + emo.getAnger());
//                        Log.i(TAG, "Disgust : " + emo.getDisgust());
//                        Log.i(TAG, "Fear : " + emo.getFear());
//                        Log.i(TAG, "Joy : " + emo.getJoy());
//                        Log.i(TAG, "Sadness : " + emo.getSadness());
//
//                    }
//
//                    @Override
//                    public void onFailure(Exception e) {
//                        Log.i(TAG, "Error is : "+e.getMessage());
//                    }
//                });
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
            }catch (Exception ex){
                Log.i(TAG, ex.getMessage());
            }
            return null;
        }
    }
}

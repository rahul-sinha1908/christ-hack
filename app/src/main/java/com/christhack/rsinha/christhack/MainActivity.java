package com.christhack.rsinha.christhack;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.christhack.rsinha.christhack.database.SharedP;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentEmotion;
import com.ibm.watson.developer_cloud.alchemy.v1.model.LanguageSelection;
import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.http.ServiceCallback;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private EditText editText, collegeText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.sample_id);
        collegeText=(EditText)findViewById(R.id.college_id);
        button=(Button) findViewById(R.id.process_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new FetchData(editText.getText().toString()).execute();
                SharedP.setValues(MainActivity.this, "name", editText.getText().toString());
                SharedP.setValues(MainActivity.this, "college", collegeText.getText().toString());
                Toast.makeText(MainActivity.this, "You are successfully logged in.", Toast.LENGTH_LONG).show();
            }
        });
        //service.getEmotion();
    }
}

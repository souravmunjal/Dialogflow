package com.example.souravmunjal.dialogflowproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;

public class MainActivity extends AppCompatActivity implements AIListener {
AIService aiService;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       int permsisson=ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO);
       if(permsisson!=PackageManager.PERMISSION_GRANTED)
       {
           Toast.makeText(this, "Permisiion denied", Toast.LENGTH_SHORT).show();
           makeRequest();
       }
        final AIConfiguration config = new AIConfiguration("07e59d2069ab4ad2bb0c3f0ffa88fc76",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        aiService=AIService.getService(this,config);
        aiService.setListener(this);
        textView=(TextView) findViewById(R.id.show);
    }
    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO},
                201);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 201: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {

                } else {
                }
                return;
            }
        }
    }
    @Override
    public void onResult(AIResponse result) {
        Toast.makeText(this, "the result is "+ result.toString(), Toast.LENGTH_SHORT).show();
        Result resultl=result.getResult();
        textView.setText("query is "+resultl.getResolvedQuery()+"action"+resultl.getContexts().toString());

    }

    @Override
    public void onError(AIError error) {

    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }
    public void onclickbutton(View v)
    {
aiService.startListening();
    }
}

package com.example.prasanth.googleanalyticsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;
import com.example.prasanth.googleanalyticsapplication.activity.AnalyticsApplication;
import com.example.prasanth.googleanalyticsapplication.activity.VideoViewCountAcitivity;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.tasks.RuntimeExecutionException;

import java.util.Random;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Tracker tracker;
    private String screenName = "Main Activity";
    private Button nextActivityButton;
    final int min = 20;
    final int max = 80;
    int random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        random = new Random().nextInt((max - min) + 1) + min;

        /*Initializing Crashlytics*/
        Fabric.with(this, new Crashlytics());


        //Use this details to get user information along with the crash reports
       /* To check this user details in the console,
        go to the bottom of a issue page under section 'Recent Activity'

        check this link for reference
        https://stackoverflow.com/questions/34888420/crashlytics-how-to-see-user-name-email-id-in-crash-details
      */
        Crashlytics.setUserEmail("prashanth@gmail.com");
        Crashlytics.setUserName("Prashanth");


        nextActivityButton = (Button) findViewById(R.id.nextAcitivityButton);
        Button hydBtn = (Button) findViewById(R.id.hyderabadBtn);
        Button delhiBtn = (Button) findViewById(R.id.delhiBtn);
        Button ahmedabadBtn = (Button) findViewById(R.id.ahmedabadBtn);
        Button vizag = (Button) findViewById(R.id.vizagBtn);

        //This button is to force a crash in app and also to know about crashlytics
        Button forceCrashBtn = (Button) findViewById(R.id.forceCrashBtn);

        /*Initializing Google Analytics analytics*/
        AnalyticsApplication analyticsApplication = (AnalyticsApplication) getApplication();
        tracker = analyticsApplication.getDefaultTracker();

        nextActivityButton.setOnClickListener(this);
        hydBtn.setOnClickListener(this);
        forceCrashBtn.setOnClickListener(this);
        delhiBtn.setOnClickListener(this);
        ahmedabadBtn.setOnClickListener(this);
        vizag.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*Setting screen name for google analytics*/
        Log.i("ScreenName", "Setting Screen Name:" + screenName);
        tracker.setScreenName(screenName);
        tracker.send(new HitBuilders.ScreenViewBuilder()
                .build());
       /* GoogleAnalytics.getInstance(this).dispatchLocalHits();*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextAcitivityButton:
                Intent intent = new Intent(this, VideoViewCountAcitivity.class);
                startActivity(intent);
                break;
            case R.id.hyderabadBtn:
                tracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Hyderabd")
                        .setAction("Watching")
                        .setLabel("RJ Ram")
                        .build());
                break;
            case R.id.forceCrashBtn:
                throw new RuntimeException("You got a crash");
            case R.id.delhiBtn:
                tracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Delhi")
                        .setAction("Listen")
                        .setLabel("Rj Seenu")
                        .build());
                break;
            case R.id.ahmedabadBtn:
                tracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Ahmedabad")
                        .setAction("Watch")
                        .setLabel("Rj Rani")
                        .build());
                break;
            case R.id.vizagBtn:
                tracker.set("&uid", String.valueOf(random));
                tracker.setClientId(String.valueOf(random));
                tracker.send(new HitBuilders.EventBuilder()
                        .setCategory("City_Vizag")
                        .build());
            default:
                break;
        }
    }
}

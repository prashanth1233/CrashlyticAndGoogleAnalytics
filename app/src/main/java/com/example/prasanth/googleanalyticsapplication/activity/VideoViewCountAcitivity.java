package com.example.prasanth.googleanalyticsapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.prasanth.googleanalyticsapplication.R;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class VideoViewCountAcitivity extends AppCompatActivity implements View.OnClickListener {

    private Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view_count_acitivity);
        AnalyticsApplication analyticsApplication = (AnalyticsApplication) getApplication();
        tracker = analyticsApplication.getDefaultTracker();
     /*   tracker.setLocation("MUMBAI");*/

        Button addEventBtn = (Button) findViewById(R.id.addSecondEvent);
        addEventBtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tracker.setScreenName("VideoCountScreen");
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addSecondEvent:
                tracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Delhi")
                        .setAction("Listening")
                        .setLabel("RJ Raunac")
                        .build());
            default:
                break;
        }
    }
}

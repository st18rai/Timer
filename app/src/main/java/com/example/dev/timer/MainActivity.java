package com.example.dev.timer;

import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button mButton;
    private String currentTime;
    private Handler mHandler;
    private Boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button) findViewById(R.id.button);

        mHandler = new Handler();

        if (savedInstanceState != null) {
            running = savedInstanceState.getBoolean("Running");
            Log.i("Mylog", "onCreate() " + running.toString());

            if (!running)
                timer();
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer();
            }
        });
    }

    public void timer() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                currentTime = Calendar.getInstance().getTime().toString();
                mTextView.setText(currentTime);

                mHandler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putBoolean("Running", running);
    }
}

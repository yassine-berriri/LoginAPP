package com.example.loginapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginapp.R;
import com.example.loginapp.tools.Helpers;

import java.util.concurrent.atomic.AtomicBoolean;

public class StopwatchActivity extends AppCompatActivity {

    TextView textView;
    Thread thread;
    int second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        textView = findViewById(R.id.textView_stopwatch_result);
        second = 0;
        textView.setText(String.valueOf(second));

        Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (second >= 0) {
                    runOnUiThread( () -> {
                        textView.setText(Helpers.showSeconds(second));
                    });
                    second = second - 5;
                    android.os.SystemClock.sleep(5000);
                }
            }
        };

        thread = new Thread(runnable);
        thread.start();

        /*
        thread =new Thread( new Runnable() {
            @Override
            public void run() {
                second -= 1;
                if (second == -1) {
                    minute -= 1;
                    second = 59;
                }
                if (second == 0 && minute == 0) {
                    second = 0;
                    minute = 0;
                } else if (btnPause == false) {

                    handler.postDelayed(thread,1000);
                    //run();
                } else if (btnPause) {
                    result.setText(minute + ":" + second);
                }
                runOnUiThread(() -> {
                    result.setText(minute + ":" + second);
                });
            }
        });



        thread.start();
        */
        //Lancer la Thread

        //isThreadRunnning.set(true);
        //thread.start();

        this.findViewById(R.id.button_stopwatch_button30s).setOnClickListener( (view) -> {
            second = 30;
          /*  if(thread.isInterrupted()){
                thread.run();
            }*/
             if (!thread.isAlive()) {
                thread.run();
             }


            //minute=0; second=30;
           // isThreadRunnning.set(true);
            //new Handler().postDelayed(runnable,1000); btnPause=false;
            //new Handler().handleMessage();
            // isThreadRunnning.set(true);
           // onPause();
            });

        this.findViewById(R.id.button_stopwatch_button60s).setOnClickListener((view) -> {
            second = 60;
            if (!thread.isAlive()) {
                thread.start();
            }
           /* if ( thread != null && thread.isInterrupted() ) {
                thread.run();
            }*/

            // Log.d("i'm heeere", "i'm heeere: ");
            //minute=1; second=0;
            //new Handler().postDelayed(runnable,1000); btnPause=false;
        });

        this.findViewById(R.id.button_stopwatch_button90s).setOnClickListener((view)->{
            second=90;
            if(!thread.isAlive()){
                thread.start();
            }
        });

        this.findViewById(R.id.button_stopwatch_button120s).setOnClickListener((view)->{
            second=120;
            if(!thread.isAlive()){
                thread.start();
            }
        });



        this.findViewById(R.id.button_stopwatch_pause).setOnClickListener((view)->{
            thread.interrupt();
        });

    }



}
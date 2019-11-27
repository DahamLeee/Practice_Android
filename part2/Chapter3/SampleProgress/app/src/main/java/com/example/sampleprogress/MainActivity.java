package com.example.sampleprogress;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog;
    int value = 0;
    int add = 1;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setIndeterminate(false);
        progressBar.setProgress(value);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            value = value + add;
                            if(value >= 100 || value <= 0){
                                add = -add;
                            }
                            // 이제 여기서 알아서 원하는거 async 로 해주면 될듯
                            handler.post(new Runnable(){
                                @Override
                                public void run() {
                                    progressBar.setProgress(value);
                                }
                            });
                            try{
                                Thread.sleep(100);
                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }
                });
                
                t.start();
//                dialog = new ProgressDialog(MainActivity.this);
//                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); //SPINNER는 원, HORIZONTAL은 0~100퍼
//                dialog.setMessage("데이터를 확인하는 중입니다.");
//
//                dialog.show();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                progressBar.setProgress(0);
            }
        });
    }
}

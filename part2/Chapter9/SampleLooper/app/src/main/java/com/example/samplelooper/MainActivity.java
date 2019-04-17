package com.example.samplelooper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    Handler handler = new Handler();

    ProcessThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                Message message = Message.obtain();
                message.obj = input;

                thread.processHandler.sendMessage(message);
            }
        });
        thread = new ProcessThread();
    }

    class ProcessThread extends Thread{
        ProcessHandler processHandler = new ProcessHandler();

        public void run(){
            Looper.prepare();
            Looper.loop();
        }

        class ProcessHandler extends Handler{

            public void handleMessage(Message msg){
                final String output = msg.obj + " from thread."; // 별도의 Thread 에서 handler를 통해 text 붙이고
                handler.post(new Runnable() { // Runnable 객체의 post 메서드를 통해 Main Thread에 다시 보냄
                    @Override
                    public void run() {
                        textView.setText(output);
                    }
                });
            }
        }
    }
}

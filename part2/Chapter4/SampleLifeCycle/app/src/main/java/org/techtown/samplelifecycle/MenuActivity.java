package org.techtown.samplelifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        println("Menu : onCreate 호출됨");
    }
    public void println(String data){
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
        Log.d("Main", data);
    }
    @Override
    protected void onPause() {
        super.onPause();
        println("Menu : onPause 호출됨");

    }

    @Override
    protected void onResume() {
        super.onResume();
        println("Menu : onResume 호출됨");

    }

    @Override
    protected void onStart() {
        super.onStart();
        println("Menu : onStart 호출됨");
    }

    @Override
    protected void onStop() {
        super.onStop();
        println("Menu : onStop 호출됨");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        println("Menu : onDestroy 호출됨");
    }
}

package com.example.sampledrawable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageButton like;
    ImageButton hate;
    TextView likeCount;
    TextView hateCount;

    String temp = null;
    int temp1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        likeCount = findViewById(R.id.likeCount);
        hateCount = findViewById(R.id.hateCount);

        like = findViewById(R.id.like);
        like.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                like.setSelected(!like.isSelected());
                if(like.isSelected()){
                    if(hate.isSelected()){
                        hate.setSelected(!hate.isSelected());
                        hateDecrease();
                    }
                    likeIncrease();
                }
                else{
                    likeDecrease();
                }
            }
        });
        hate = findViewById(R.id.hate);
        hate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                hate.setSelected(!hate.isSelected());
                if(hate.isSelected()){
                    if(like.isSelected()){
                        like.setSelected(!like.isSelected());
                        likeDecrease();
                    }
                    hateIncrease();
                }
                else{
                    hateDecrease();
                }
            }
        });
    }

    public void likeIncrease(){
        temp = likeCount.getText().toString();
        temp1 = Integer.parseInt(temp);
        temp1 += 1;
        temp = String.valueOf(temp1);
        likeCount.setText(temp);
    }
    public void likeDecrease(){
        temp = likeCount.getText().toString();
        temp1 = Integer.parseInt(temp);
        temp1 -= 1;
        temp = String.valueOf(temp1);
        likeCount.setText(temp);
    }
    public void hateIncrease(){
        temp = hateCount.getText().toString();
        temp1 = Integer.parseInt(temp);
        temp1 += 1;
        temp = String.valueOf(temp1);
        hateCount.setText(temp);
    }
    public void hateDecrease(){
        temp = hateCount.getText().toString();
        temp1 = Integer.parseInt(temp);
        temp1 -= 1;
        temp = String.valueOf(temp1);
        hateCount.setText(temp);
    }
}

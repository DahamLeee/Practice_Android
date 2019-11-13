package com.example.sampleprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                insertPeron();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryPerson();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePerson();
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePerson();
            }
        });
    }

    public void println(String data){
        textView.append(data + "\n");
    }

    public void insertPeron(){
        println("insertPerson 호출됨");

        String uriString = "content://com.example.sampleprovider/person";
        Uri uri = new Uri.Builder().build().parse(uriString);

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        String[] colums = cursor.getColumnNames();
        println("columns counts -> " + colums.length);
        for(int i = 0; i < colums.length; i++){
            println("#" + i + " : " + colums[i]);
        }

        ContentValues values = new ContentValues();
        values.put("name", "john");
        values.put("age", 20);
        values.put("mobile", "010-1000-1000");

        uri = getContentResolver().insert(uri, values);
        println("insert 결과 -> " + uri.toString());
    }

    public void queryPerson(){
        try{
            String uriString = "content://com.example.sampleprovider/person";
            Uri uri = new Uri.Builder().build().parse(uriString);

            String[] columns = new String[]{"name", "age", "mobile"};
            Cursor cursor = getContentResolver().query(uri, columns, null, null, "name ASC");
            println("query 결과: " + cursor.getCount());

            int index = 0;
            while (cursor.moveToNext()) { // return 된 cursor 객체를 계속 tracing 해주는 거임
                String name = cursor.getString(cursor.getColumnIndex(columns[0]));
                int age = cursor.getInt(cursor.getColumnIndex(columns[1]));
                String mobile = cursor.getString(cursor.getColumnIndex(columns[2]));

                println("#" + index + " -> " + name + ", " + age + ", " + mobile);
                index += 1;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updatePerson(){
        String uriString = "content://com.example.sampleprovider/person";
        Uri uri = new Uri.Builder().build().parse(uriString);

        String selection = "mobile = ?";
        String[] selectionArgs = new String[] {"010-1000-1000"};
        ContentValues updateValue = new ContentValues();
        updateValue.put("mobile" , "010-2000-2000");
        int count = getContentResolver().update(uri, updateValue, selection, selectionArgs);
        println("update 결과: " + count);
    }

    public void deletePerson(){
        String uriString = "content://com.example.sampleprovider/person";
        Uri uri = new Uri.Builder().build().parse(uriString);

        String selection = "name = ?";
        String[] selectionArgs = new String[] {"john"};
        // 이런식으로 selection 에 ?가 있으면 selectionArgs 에 배열 형식으로 value 를 넘겨주면 됨.
        int count = getContentResolver().delete(uri, selection, selectionArgs);
        println("delete 결과: " + count);
    }
}

package com.personal.search_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv_result = findViewById(R.id.tv_result);

        Intent intent = getIntent();
        intent.getStringExtra("result");
        tv_result.setText(intent.getStringExtra("result"));



    }
}
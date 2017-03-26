package com.example.karan.tryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_event);
        Intent intent = getIntent();
        String event = intent.getStringExtra(MyListActivity.EXTRA_MESSAGE);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(event);
    }
}

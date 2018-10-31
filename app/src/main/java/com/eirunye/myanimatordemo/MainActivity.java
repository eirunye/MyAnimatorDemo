package com.eirunye.myanimatordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eirunye.myanimatordemo.view.MyView;

public class MainActivity extends AppCompatActivity {

    MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        myView = findViewById(R.id.myView);
    }

    public void start(View v) {
//        myView.startAnimator();
    }
}

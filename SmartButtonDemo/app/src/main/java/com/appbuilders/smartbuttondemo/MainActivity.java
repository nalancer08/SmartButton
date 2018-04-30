package com.appbuilders.smartbuttondemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appbuilders.smartbutton.SmartButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmartButton b = new SmartButton(this);

        // Normal button
        SmartButton programmatically = findViewById(R.id.programmatically_button);
        programmatically.setTintColor(Color.CYAN);
        programmatically.setBorderRadius(50);
        programmatically.setStrokeColor(Color.GREEN);
        programmatically.setStrokeWidth(5);
        programmatically.setBackgroundColor(Color.RED);
    }
}

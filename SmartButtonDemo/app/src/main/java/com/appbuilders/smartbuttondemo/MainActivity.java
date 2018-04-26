package com.appbuilders.smartbuttondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appbuilders.smartbutton.SmartButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmartButton b = new SmartButton(this);
    }
}

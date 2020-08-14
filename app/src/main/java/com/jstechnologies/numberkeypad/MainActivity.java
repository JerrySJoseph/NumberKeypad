package com.jstechnologies.numberkeypad;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.jstechnologies.numberkeypadview.NumberKeypadView;

public class MainActivity extends AppCompatActivity {

    NumberKeypadView keypadView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keypadView=findViewById(R.id.numberkeypad);
        keypadView.setOnEntryComplete(new NumberKeypadView.OnEntryComplete() {
            @Override
            public void onComplete(String text) {
                Toast.makeText(MainActivity.this,"Entry Complete :"+text,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
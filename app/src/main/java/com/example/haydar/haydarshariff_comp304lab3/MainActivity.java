package com.example.haydar.haydarshariff_comp304lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Intent anIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button canvasBtn = findViewById(R.id.examleBtn1);
        canvasBtn.setOnClickListener(v -> {
            anIntent = new Intent(MainActivity.this, CanvasActivity.class);
            startActivity(anIntent);
        });

        Button frameBtn = findViewById(R.id.examleBtn2);
        frameBtn.setOnClickListener(v -> {
            anIntent = new Intent(MainActivity.this, FrameActivity.class);
            startActivity(anIntent);
        });

        Button tweenBtn = findViewById(R.id.examleBtn3);
        tweenBtn.setOnClickListener(v -> {
            anIntent = new Intent(MainActivity.this, TweenActivity.class);
            startActivity(anIntent);
        });
    }
}

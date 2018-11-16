package com.example.haydar.haydarshariff_comp304lab3;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.concurrent.ThreadLocalRandom;

public class FrameActivity extends AppCompatActivity {

    private AnimationDrawable animationFrame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        findViewById(R.id.bStart).setOnClickListener(v -> startAnimation());
        findViewById(R.id.bStop).setOnClickListener(v -> stopAnimation());
    }

    private void startAnimation() {
        Drawable[] frames = new Drawable[5];
        frames[0] = getDrawable(R.drawable.dbz1);
        frames[1] = getDrawable(R.drawable.dbz2);
        frames[2] = getDrawable(R.drawable.dbz3);
        frames[3] = getDrawable(R.drawable.dbz4);
        frames[4] = getDrawable(R.drawable.dbz5);


        animationFrame = new AnimationDrawable();
        animationFrame.setOneShot(false); // loop continuously
        for (Drawable frame : frames) {
            int randomDuration = ThreadLocalRandom.current().nextInt(100, 500 + 1);
            animationFrame.addFrame(frame, randomDuration);
        }

        ImageView img = findViewById(R.id.ivAnimation);
        img.setBackgroundDrawable(animationFrame);

        animationFrame.start();
    }

    private void stopAnimation() {
        animationFrame.stop();
    }

}

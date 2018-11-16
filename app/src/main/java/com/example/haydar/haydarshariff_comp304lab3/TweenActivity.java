package com.example.haydar.haydarshariff_comp304lab3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class TweenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);

        performAnimation();
    }

    private void performAnimation() {
        ImageView reusableImageView = findViewById(R.id.ivMoon);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.moon);
        animation.setAnimationListener(new MyAnimationListener());
        animation.setRepeatCount(Animation.INFINITE);
        reusableImageView.startAnimation(animation);
    }

    class MyAnimationListener implements Animation.AnimationListener {

        public void onAnimationEnd(Animation animation) {
            animation.start();
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }
}

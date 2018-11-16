package com.example.haydar.haydarshariff_comp304lab3;

import android.content.Intent;
import android.graphics.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;


public class CanvasActivity extends AppCompatActivity {

    Spinner aSpinner;
    RadioGroup aRadioGroup;
    TextView aTextView, aTextView2;
    ImageView anImageView;

    Canvas aCanvas;
    Paint aPaint;
    int currentX;
    int currentY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        //creating a bitmap as content view for the image
        Point windowSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(windowSize);
        Bitmap bitmap = Bitmap.createBitmap(windowSize.x, windowSize.y, Bitmap.Config.ARGB_8888);
        aCanvas = new Canvas(bitmap);
        aCanvas.drawColor(Color.LTGRAY); //background

        anImageView = findViewById(R.id.ImageViewForDrawing);
        anImageView.setImageBitmap(bitmap);

        aTextView = findViewById(R.id.textView1);
        aTextView2 = findViewById(R.id.textView);

        aCanvas.drawColor(Color.LTGRAY);

        String[] thicknessValues = {"10", "15", "20", "25", "30"};
        aSpinner = findViewById(R.id.spThickness);
        aSpinner.setAdapter(new ArrayAdapter<>(
                CanvasActivity.this, android.R.layout.simple_spinner_item, thicknessValues
        ));
        aSpinner.setSelection(1);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(CanvasActivity.this, MainActivity.class));
            return false;
        }
        return move(keyCode);
    }

    public void onClearButtonClicked(View v) {
        restartCanvas();
    }

    private void init() {
        aSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                createPaint();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        aRadioGroup = findViewById(R.id.rgColor);
        aRadioGroup.setOnCheckedChangeListener((group, checkedId) -> createPaint());

        findViewById(R.id.upAK).setOnClickListener(v -> move(KeyEvent.KEYCODE_DPAD_UP));
        findViewById(R.id.downAK).setOnClickListener(v -> move(KeyEvent.KEYCODE_DPAD_DOWN));
        findViewById(R.id.rightAK).setOnClickListener(v -> move(KeyEvent.KEYCODE_DPAD_RIGHT));
        findViewById(R.id.leftAK).setOnClickListener(v -> move(KeyEvent.KEYCODE_DPAD_LEFT));

        restartCanvas();
    }

    private boolean move(int keyCode) {
        boolean handled = true;
        final int magnitude = 8;

        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                drawLine(0, -magnitude);
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                drawLine(0, magnitude);
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                drawLine(-magnitude, 0);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                drawLine(magnitude, 0);
                break;
            default:
                handled = false;
        }

        if (handled) {
            anImageView.requestFocus();
        }

        return handled;
    }

    private void drawLine(int changesX, int changesY) {
        if (aPaint == null) {
            init();
        }

        int newX = currentX + changesX;
        int newY = currentY + changesY;
        aCanvas.drawLine(currentX, currentY, newX, newY, aPaint);

        currentX = newX;
        currentY = newY;

        if (changesY != 0) {
            aTextView2.setText(getString(R.string.y, currentY));
        }
    }

    private void restartCanvas() {

        aCanvas.drawColor(Color.LTGRAY);

        createPaint();

        currentX = anImageView.getWidth() / 2;
        currentY = anImageView.getHeight() / 2;
        aCanvas.drawPoint(currentX, currentY, aPaint);
        aTextView2.setText(getString(R.string.y, currentY));
    }

    private void createPaint() {
        Paint paint = new Paint();

        int color;
        int selectedColorId = aRadioGroup.getCheckedRadioButtonId();
        if (selectedColorId == R.id.rbRed) {
            color = Color.RED;
        } else if (selectedColorId == R.id.rbYellow) {
            color = Color.YELLOW;
        } else {
            color = Color.GREEN;
        }

        int thickness = Integer.parseInt(aSpinner.getSelectedItem().toString());

        paint.setColor(color);
        paint.setStrokeWidth(thickness);
        aPaint = paint;
    }
}

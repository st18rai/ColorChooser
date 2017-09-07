package com.st18apps.color;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar red;
    private SeekBar green;
    private SeekBar blue;
    private LinearLayout palette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        red = (SeekBar) findViewById(R.id.seekBarR);
        green = (SeekBar) findViewById(R.id.seekBarG);
        blue = (SeekBar) findViewById(R.id.seekBarB);
        palette = (LinearLayout) findViewById(R.id.palette);

        red.setOnSeekBarChangeListener(this);
        green.setOnSeekBarChangeListener(this);
        blue.setOnSeekBarChangeListener(this);

    }

    private void updateColor() {
        int redColor, greenColor, blueColor;
        redColor = red.getProgress();
        greenColor = green.getProgress();
        blueColor = blue.getProgress();

        palette.setBackgroundColor(0xff000000 + redColor * 0x10000 + greenColor * 0x100
                + blueColor);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        updateColor();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}

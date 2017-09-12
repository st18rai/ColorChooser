package com.st18apps.color;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar red;
    private SeekBar green;
    private SeekBar blue;
    private LinearLayout palette;
    private TextView tvRed;
    private TextView tvGreen;
    private TextView tvBlue;
    private TextView tvCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        red = (SeekBar) findViewById(R.id.seekBarR);
        green = (SeekBar) findViewById(R.id.seekBarG);
        blue = (SeekBar) findViewById(R.id.seekBarB);
        palette = (LinearLayout) findViewById(R.id.palette);
        tvRed = (TextView) findViewById(R.id.textViewR);
        tvGreen = (TextView) findViewById(R.id.textViewG);
        tvBlue = (TextView) findViewById(R.id.textViewB);
        tvCode = (TextView) findViewById(R.id.textViewColorCode);

        red.setOnSeekBarChangeListener(this);
        green.setOnSeekBarChangeListener(this);
        blue.setOnSeekBarChangeListener(this);

    }

    private void updateColor() {
        int redColor, greenColor, blueColor, colorCode;
        String colorCodeText;
        redColor = red.getProgress();
        greenColor = green.getProgress();
        blueColor = blue.getProgress();

        colorCode = 0xff000000 + redColor * 0x10000 + greenColor * 0x100
                + blueColor;
        colorCodeText = Integer.toHexString(colorCode).substring(2);
        palette.setBackgroundColor(colorCode);

        tvRed.setText(getResources().getString(R.string.red) + " " + String.valueOf(red.getProgress()));
        tvGreen.setText(getResources().getString(R.string.green) + " " + String.valueOf(green.getProgress()));
        tvBlue.setText(getResources().getString(R.string.blue) + " " + String.valueOf(blue.getProgress()));
        tvCode.setText(getResources().getString(R.string.color_code) + " " + colorCodeText);

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

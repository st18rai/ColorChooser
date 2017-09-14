package com.st18apps.color;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar sbRed;
    private SeekBar sbGreen;
    private SeekBar sbBlue;
    private LinearLayout llPalette;
    private TextView tvRed;
    private TextView tvGreen;
    private TextView tvBlue;
    private TextView tvCode;
    private EditText etColor;
    private Button button;
    private Pattern pattern;
    private Matcher matcher;

    private static final String HEX_PATTERN = "^[A-Fa-f0-9]{6}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbRed = (SeekBar) findViewById(R.id.seekBarR);
        sbGreen = (SeekBar) findViewById(R.id.seekBarG);
        sbBlue = (SeekBar) findViewById(R.id.seekBarB);
        llPalette = (LinearLayout) findViewById(R.id.palette);
        tvRed = (TextView) findViewById(R.id.textViewR);
        tvGreen = (TextView) findViewById(R.id.textViewG);
        tvBlue = (TextView) findViewById(R.id.textViewB);
        tvCode = (TextView) findViewById(R.id.textViewColorCode);
        etColor = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        sbRed.setOnSeekBarChangeListener(this);
        sbGreen.setOnSeekBarChangeListener(this);
        sbBlue.setOnSeekBarChangeListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkColor(etColor.getText().toString()))
                    setColor();
                else
                    Toast.makeText(getApplicationContext(), "Enter correct data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateColor() {
        int redColor, greenColor, blueColor, colorCode;
        String colorCodeText;
        redColor = sbRed.getProgress();
        greenColor = sbGreen.getProgress();
        blueColor = sbBlue.getProgress();

        colorCode = 0xff000000 + redColor * 0x10000 + greenColor * 0x100
                + blueColor;
        colorCodeText = Integer.toHexString(colorCode).substring(2);
        llPalette.setBackgroundColor(colorCode);

        tvRed.setText(getResources().getString(R.string.red) + " " + String.valueOf(sbRed.getProgress()));
        tvGreen.setText(getResources().getString(R.string.green) + " " + String.valueOf(sbGreen.getProgress()));
        tvBlue.setText(getResources().getString(R.string.blue) + " " + String.valueOf(sbBlue.getProgress()));
        etColor.setText(colorCodeText);

    }

    private void setColor() {
        String enteredColor = etColor.getText().toString();

        llPalette.setBackgroundColor(Color.parseColor("#" + enteredColor));

        sbRed.setProgress(Integer.parseInt(enteredColor.substring(0, 2), 16));
        sbGreen.setProgress(Integer.parseInt(enteredColor.substring(2, 4), 16));
        sbBlue.setProgress(Integer.parseInt(enteredColor.substring(4, 6), 16));
    }

    private boolean checkColor(final String hex) {
        pattern = Pattern.compile(HEX_PATTERN);
        matcher = pattern.matcher(hex);

        return matcher.matches();
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

package com.grupo7.hci.smarthome.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class RefrigeratorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrigerator);

        /*final SeekBar temperature = findViewById(R.id.seekBar_refrigerator_temperature);
        final SeekBar freezer = findViewById(R.id.seekBar_refrigerator_freezer);
        final RadioGroup radioGroupMode = findViewById(R.id.radioGroup_refrigerator_mode);

        RadioButton[] selectedButtons = new RadioButton[1];
        int rb0 = getResources().getIdentifier("radioButton_refrigerator_" + apiResponse.mode.toLowerCase() +"mode",
                "id", getPackageName());
        selectedButtons[0] = findViewById(rb0);
        for(RadioButton rb : selectedButtons)
            rb.setChecked(true);

        temperature.setMax(6);
        temperature.setProgress(apiResponse.temperature);
        freezer.setMax(12);
        freezer.setProgress(apiResponse.temperature);

        radioGroupMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setDeviceStatus(deviceId, checkedRadioButton.getText().toString().toLowerCase());
            }
        });

        temperature.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //t1.setText(progress); //textview with value
                //Toast.makeText(getApplicationContext(), String.valueOf(progress),Toast.LENGTH_LONG).show();
                setDeviceStatus(deviceId, "temperature", ((Integer)progress).toString());
            }
        });

        freezer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //t1.setText(progress); //textview with value
                //Toast.makeText(getApplicationContext(), String.valueOf(progress),Toast.LENGTH_LONG).show();
                setDeviceStatus(deviceId, "freezerTemperature", ((Integer)progress).toString());
            }
        });*/


//        .done(function(data, textStatus, jqXHR) {
//            var result = data.result;
//            $('input[name=quantity]').val(result.temperature);
//            $('input[name=freez-quantity]').val(result.freezerTemperature);
//            $("#" + result.mode).prop("checked", true);
    }

    protected void onChangeMode() {
//        $('input[name=mode]').on("change", function() {
//            var state = $("form input[name='mode']:checked").val();
//            api.device.executeAction(device.id, "setMode", [state])
//      .done(function(data, textStatus, jqXHR) {
//            })
//      .fail(function(jqXHR, textStatus, errorThrown) {
//                console.log(jqXHR.responseText);
//            })
    }

    protected void onChangeTemperature() {
//        $("#temperature").on("mouseup", function() {
//            var temperature = $("#temperature").val()
//            api.device.executeAction(device.id, "setTemperature", [temperature])
//      .done(function(data, textStatus, jqXHR) {
//            })
    }

    protected void onChangeFreezerTemperature() {
//        $("#temperature").on("mouseup", function() {
//            var temperature = $("#temperature").val()
//            api.device.executeAction(device.id, "setTemperature", [temperature])
//      .done(function(data, textStatus, jqXHR) {
//            })
    }
}

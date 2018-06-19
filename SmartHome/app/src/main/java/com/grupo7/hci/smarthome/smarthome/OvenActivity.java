package com.grupo7.hci.smarthome.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;

import org.json.JSONObject;

public class OvenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oven);

        /*final Switch switchOn = findViewById(R.id.switch_oven_on);
        final SeekBar temperature = findViewById(R.id.seekBar_oven_temp);
        final RadioGroup radioGroupHeat = findViewById(R.id.radioGroup_oven_heat);
        final RadioGroup radioGroupGrill = findViewById(R.id.radioGroup_oven_grill);
        final RadioGroup radioGroupConvection = findViewById(R.id.radioGroup_oven_convection);

        JSONObject apiResponse = getDeviceStatus(deviceId);
        if(apiResponse.status.equals("on")) {
            switchOn.setChecked(true);
            switchOn.setText(R.string.on);
        } else {
            switchOn.setChecked(false);
        }

        RadioButton[] selectedButtons = new RadioButton[3];
        int rb0 = getResources().getIdentifier("radioButton_oven_" + apiResponse.heat.toLowerCase() +"heat",
                "id", getPackageName());
        int rb1 = getResources().getIdentifier("radioButton_oven_" + apiResponse.grill +"grill",
                "id", getPackageName());
        int rb2 = getResources().getIdentifier("radioButton_oven_" + apiResponse.convection +"convection",
                "id", getPackageName());
        selectedButtons[0] = findViewById(rb0);
        selectedButtons[1] = findViewById(rb1);
        selectedButtons[2] = findViewById(rb2);
        for(RadioButton rb : selectedButtons)
            rb.setChecked(true);

        temperature.setMax(230 - 90);
        temperature.setProgress(apiResponse.temperature);

        switchOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    switchOn.setText(R.string.on);
                else
                    switchOn.setText(R.string.off);
                setDeviceStatus(deviceId, "on-status", isChecked);
            }
        });

        radioGroupHeat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setDeviceStatus(deviceId, checkedRadioButton.getText().toString().toLowerCase());
            }
        });

        radioGroupGrill.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setDeviceStatus(deviceId, checkedRadioButton.getText().toString().toLowerCase());
            }
        });

        radioGroupConvection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
        });*/

//        .done(function(data, textStatus, jqXHR) {
//            var result = data.result;
//            if (result.status === "on") {
//                $("#on-status").text("On");
//                $("#on-switch").prop("checked", true);
//            } else {
//                $("#on-status").text("Off");
//            }
//            $("#temperature").val(result.temperature);
//            $("#heat-text").text(result.temperature);
//            $("#heat" + result.heat).prop("checked", true);
//            $("#grill" + result.grill).prop("checked", true);
//            $("#conv" + result.convection).prop("checked", true);
    }

    protected void onTurnOnOrOff() {
//        $("#on-switch").on("click", function() {
//            var status = $("#on-status").text();
//            if(status === "On") {
//                api.device.executeAction(device.id, "turnOff", [])
//        .done(function(data, textStatus, jqXHR) {
//                    $("#on-status").text("Off");
//                })
//        else {
//            api.device.executeAction(device.id, "turnOn", [])
//        .done(function(data, textStatus, jqXHR) {
//                $("#on-status").text("On");
//            })
    }

    protected void onChangeHeat() {
//        $('input[name=heat]').on("change", function() {
//            var state = $("form input[name='heat']:checked").val();
//            api.device.executeAction(device.id, "setHeat", [state])
//      .done(function(data, textStatus, jqXHR) {
//            })
    }

    protected void onChangeGrill() {
//        $('input[name=grill]').on("change", function() {
//            var state = $("form input[name='grill']:checked").val();
//            api.device.executeAction(device.id, "setGrill", [state])
//      .done(function(data, textStatus, jqXHR) {
//            })
    }

    protected void onChangeConvection() {
//        $('input[name=convection]').on("change", function() {
//            var state = $("form input[name='convection']:checked").val();
//            api.device.executeAction(device.id, "setConvection", [state])
//      .done(function(data, textStatus, jqXHR) {
//            })
    }

    protected void onChangeTemperature() {
//        $("#temperature").on("mouseup", function() {
//            var temperature = $("#temperature").val()
//            api.device.executeAction(device.id, "setTemperature", [temperature])
//      .done(function(data, textStatus, jqXHR) {
//            })
    }


}

package com.grupo7.hci.smarthome.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class LampActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp);

        /*final Switch switchOn = findViewById(R.id.switch_lamp_on);
        final SeekBar brightness = findViewById(R.id.seekBar_lamp_brightness);

        JSONObject apiResponse = getDeviceStatus(deviceId);
        if(apiResponse.status.equals("on")) {
            switchOn.setChecked(true);
            switchOn.setText(R.string.on);
        } else {
            switchOn.setChecked(false);
        }

        brightness.setMax(100);
        brightness.setProgress(apiResponse.brightness);

        switchOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    switchOn.setText(R.string.on);
                else
                    switchOn.setText(R.string.off);
                setDeviceStatus(deviceId, "on-status", isChecked);
            }
        });

        brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

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
                setDeviceStatus(deviceId, "brightness", ((Integer)progress).toString());
            }
        });*/

//        .done(function(data, textStatus, jqXHR) {
//            if (data.result.status === "on") {
//                $("#on-status").text("On");
//                $("#on-switch").prop("checked", true);
//            } else {
//                $("#on-status").text("Off");
//            }
//            $("#color").val(data.result.color);
//            $(".color-preview").css("background-color", data.result.color);
//            $("#brightness").val(data.result.brightness);
    }

    protected void onTurnOnOrOff() {
//        $("#on-switch").on("click", function() {
//            var status = $("#on-status").text()
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

    protected void onChangeColor() {
//        $("#color").on("change", function() {
//            var hexColor = $('#color :selected').val()
//            api.device.executeAction(device.id, "setColor", [hexColor])
//      .done(function(data, textStatus, jqXHR) {
//                $(".color-preview").css("background-color", hexColor);
//            })
    }

    protected void onChangeBrightness() {
//        $("#brightness").on("mouseup", function() {
//            var brightness = $("#brightness").val()
//            api.device.executeAction(device.id, "setBrightness", [brightness])
//      .done(function(data, textStatus, jqXHR) {
//            })
    }
}

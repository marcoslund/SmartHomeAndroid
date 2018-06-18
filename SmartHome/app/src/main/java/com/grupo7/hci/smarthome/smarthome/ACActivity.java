package com.grupo7.hci.smarthome.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import org.json.JSONObject;

public class ACActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac);

       /* Switch switchOn = findViewById(R.id.switch_ac_on);
        RadioGroup radioGroupMode = findViewById(R.id.radioGroup_ac_mode);
        RadioGroup radioGroupVSwing = findViewById(R.id.radioGroup_vswing);
        RadioGroup radioGroupHSwing = findViewById(R.id.radioGroup_hswing);
        RadioGroup radioGroupFanSpeed = findViewById(R.id.radioGroup_fanspeed);

        JSONObject apiResponse = getDeviceStatus(deviceId);
        if(apiResponse.status.equals("on")) {
            switchOn.setChecked(true);
        } else {
            switchOn.setChecked(false);
        }

        RadioButton[] selectedButtons = new RadioButton[4];
        selectedButtons[0] = findViewById("radioButton_ac_" + apiResponse.mode.toLowerCase());
        selectedButtons[1] = findViewById("radioButton_ac_" + apiResponse.verticalSwing +"vswing");
        selectedButtons[2] = findViewById("radioButton_ac_" + apiResponse.horizontalSwing +"hswing");
        selectedButtons[3] = findViewById("radioButton_ac_" + apiResponse.fanSpeed +"fsp");
        for(RadioButton rb : selectedButtons)
            rb.setChecked(true);

        switchOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setDeviceStatus(deviceId, "on-status", !isChecked);
            }
        });

        radioGroupMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = findViewById(checkedId);
                setDeviceStatus(deviceId, checkedRadioButton.getText().toString().toLowerCase());
            }
        });

        radioGroupVSwing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = findViewById(checkedId);
                setDeviceStatus(deviceId, checkedRadioButton.getText().toString().toLowerCase());
            }
        });

        radioGroupHSwing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = findViewById(checkedId);
                setDeviceStatus(deviceId, checkedRadioButton.getText().toString().toLowerCase());
            }
        });

        radioGroupFanSpeed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = findViewById(checkedId);
                setDeviceStatus(deviceId, checkedRadioButton.getText().toString().toLowerCase());
            }
        });

//        if(result.status === "on") {
//            $("#on-status").text("On");
//            $("#on-switch").prop("checked", true);
//        } else {
//            $("#on-status").text("Off");
//        }
//        $('input[name=quantity]').val(result.temperature);
//        $("#" + result.mode).prop("checked", true);
//        $("#vSwing" + result.verticalSwing).prop("checked", true);
//        $("#hSwing" + result.horizontalSwing).prop("checked", true);
//        $("#fanSpeed" + result.fanSpeed).prop("checked", true);
    */}

    protected void onTurnOnOrOff() {
//        if(status === "On") {
//            api.device.executeAction(device.id, "turnOff", [])
//        .done(function(data, textStatus, jqXHR) {
//                $("#on-status").text("Off");
//            })
//        else {
//            api.device.executeAction(device.id, "turnOn", [])
//        .done(function(data, textStatus, jqXHR) {
//                $("#on-status").text("On");
//            })
    }

    protected void onChangeTemperature() {
//        $("#temperature").on("mouseup", function() {
//            var temperature = $("#temperature").val()
//            api.device.executeAction(device.id, "setTemperature", [temperature])
//      .done(function(data, textStatus, jqXHR) {
//            })
    }

    protected void onChangeMode(int id) {
//        $('input[name=mode]').on("change", function() {
//            var state = $("form input[name='mode']:checked").val();
//            api.device.executeAction(device.id, "setMode", [state])
//      .done(function(data, textStatus, jqXHR) {
//            })
    }

    protected void onChangeVSwing() {
//        $('input[name=v-swing]').on("change", function() {
//            var state = $("form input[name='v-swing']:checked").val();
//            api.device.executeAction(device.id, "setVerticalSwing", [state])
//      .done(function(data, textStatus, jqXHR) {
//            })
    }

    protected void onChangeHSwing() {
//        $('input[name=h-swing]').on("change", function() {
//            var state = $("form input[name='h-swing']:checked").val();
//            api.device.executeAction(device.id, "setHorizontalSwing", [state])
//      .done(function(data, textStatus, jqXHR) {
//            })
    }

    protected void onChangeFanSpeed() {
//        $('input[name=fan-speed]').on("change", function() {
//            var state = $("form input[name='fan-speed']:checked").val();
//            api.device.executeAction(device.id, "setFanSpeed", [state])
//      .done(function(data, textStatus, jqXHR) {
//            })
    }
}

package com.grupo7.hci.smarthome.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import org.json.JSONObject;

public class DoorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);

        /*final Switch switchOpen = findViewById(R.id.switch_door_open);
        final Switch switchLock = findViewById(R.id.switch_door_lock);

        JSONObject apiResponse = getDeviceStatus(deviceId);
        if(apiResponse.status.equals("open") || apiResponse.status.equals("opening")) {
            switchOpen.setChecked(true);
            switchOpen.setText(R.string.open);
        } else {
            switchOpen.setChecked(false);
        }

        switchOpen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    switchOpen.setText(R.string.open);
                else
                    switchOpen.setText(R.string.close);
                setDeviceStatus(deviceId, "open-status", isChecked);
            }
        });

        if(apiResponse.status.equals("locked")) {
            switchLock.setChecked(true);
            switchLock.setText(R.string.lock);
        } else {
            switchLock.setChecked(false);
        }

        switchLock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    switchLock.setText(R.string.lock);
                else
                    switchLock.setText(R.string.unlock);
                setDeviceStatus(deviceId, "lock-status", isChecked);
            }
        });*/

//        .done(function(data, textStatus, jqXHR) {
//            if (data.result.status === "opened" || data.result.status === "opening") {
//                $("#open-status").text("Open");
//                $("#open-switch").prop("checked", true);
//            } else {
//                $("#open-status").text("Closed");
//            }
//            if (data.result.lock === "locked") {
//                $("#lock-status").text("Locked");
//                $("#lock-switch").prop("checked", true);
//            } else {
//                $("#lock-status").text("Unlocked");
//            }
    }

    protected void onChangeOpenStatus() {
//        $("#open-switch").on("click", function() {
//            var status = $("#open-status").text();
//            if(status === "Open") {
//                api.device.executeAction(device.id, "close", [])
//        .done(function(data, textStatus, jqXHR) {
//                    $("#open-status").text("Closed");
//                })
//        else {
//            api.device.executeAction(device.id, "open", [])
//        .done(function(data, textStatus, jqXHR) {
//                $("#open-status").text("Open");
//            })
    }

    protected void onChangeLockStatus() {
//        $("#lock-switch").on("click", function() {
//            var status = $("#lock-status").text();
//            if(status === "Locked") {
//                api.device.executeAction(device.id, "unlock", [])
//        .done(function(data, textStatus, jqXHR) {
//                    $("#lock-status").text("Unlocked");
//                })
//        else {
//            api.device.executeAction(device.id, "lock", [])
//        .done(function(data, textStatus, jqXHR) {
//                $("#lock-status").text("Locked");
//            })
    }

}

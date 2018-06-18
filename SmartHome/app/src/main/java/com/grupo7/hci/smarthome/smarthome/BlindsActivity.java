package com.grupo7.hci.smarthome.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import org.json.JSONObject;

public class BlindsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blinds);

       /* Switch switchOpen = findViewById(R.id.switch_blinds_open);

        JSONObject apiResponse = getDeviceStatus(deviceId);
        if(apiResponse.status.equals("opened") || apiResponse.status.equals("opening")) {
            switchOpen.setChecked(true);
        } else {
            switchOpen.setChecked(false);
        }

        switchOpen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setDeviceStatus(deviceId, "open-status", !isChecked);
            }
        });*/
    }

    protected void onChangeOpenStatus() {
//        if(status === "Open") {
//            api.device.executeAction(device.id, "down", [])
//        .done(function(data, textStatus, jqXHR) {
//                $("#open-status").text("Closed");
//            })
//         else {
//            api.device.executeAction(device.id, "up", [])
//        .done(function(data, textStatus, jqXHR) {
//                $("#open-status").text("Open");
//            })
    }
}

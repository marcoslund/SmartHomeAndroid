package com.grupo7.hci.smarthome.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DoorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);

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

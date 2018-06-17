package com.grupo7.hci.smarthome.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ACActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac);

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
    }

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

    // protected void onChangeMode(id) {
//        $('input[name=mode]').on("change", function() {
//            var state = $("form input[name='mode']:checked").val();
//            api.device.executeAction(device.id, "setMode", [state])
//      .done(function(data, textStatus, jqXHR) {
            //})
    //}

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

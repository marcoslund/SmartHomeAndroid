package com.grupo7.hci.smarthome.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OvenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oven);

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

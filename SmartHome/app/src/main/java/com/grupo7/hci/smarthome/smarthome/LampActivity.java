package com.grupo7.hci.smarthome.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LampActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp);

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

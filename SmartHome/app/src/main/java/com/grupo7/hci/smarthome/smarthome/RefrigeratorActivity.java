package com.grupo7.hci.smarthome.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RefrigeratorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrigerator);

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

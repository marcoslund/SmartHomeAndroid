package com.grupo7.hci.smarthome.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RefrigeratorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrigerator);

        /*RadioGroup radioGroupMode = findViewById(R.id.radioGroup_refrigerator_mode);

        RadioButton[] selectedButtons = new RadioButton[1];
        selectedButtons[0] = findViewById("radioButton_refrigerator_" + apiResponse.mode.toLowerCase() +"mode");
        for(RadioButton rb : selectedButtons)
            rb.setChecked(true);

        radioGroupMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = findViewById(checkedId);
                setDeviceStatus(deviceId, checkedRadioButton.getText().toString().toLowerCase());
            }
        });*/

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

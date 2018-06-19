package com.grupo7.hci.smarthome.smarthome;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DoorActivity extends AppCompatActivity {

    private String requestTag;
    private Device dev;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);

        context = this.getApplicationContext();

        final Switch switchOpen = (Switch) findViewById(R.id.switch_door_open);
        final Switch switchLock = (Switch) findViewById(R.id.switch_door_lock);


        requestTag = ApiURLs.getInstance(context).executeAction(dev, "getState", new ArrayList(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject result = response.getJSONObject("result");
                    String status = result.getString("status");
                    String lock = result.getString("lock");
                    if (switchOpen != null) {
                        if (status == "opened" || status == "opening") {
                            switchOpen.setText(R.string.open);
                            switchOpen.setChecked(true);
                        } else {
                            switchOpen.setText(R.string.close);
                        }
                    }
                    if(switchLock != null) {
                        if (status == "locked") {
                            switchLock.setText(R.string.lock);
                            switchLock.setChecked(true);
                        } else {
                            switchLock.setText(R.string.unlock);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("getState", "Error on Blind get State");
                // TODO toast init
                error.printStackTrace();
            }
        });


        switchOpen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String actionName;
                final boolean auxChecked = isChecked;
                if (isChecked)
                    actionName = "open";
                else
                    actionName = "close";
                requestTag = ApiURLs.getInstance(context).executeAction(dev, actionName, new ArrayList(), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (auxChecked)
                            switchOpen.setText(R.string.open);
                        else
                            switchOpen.setText(R.string.close);
                        // TODO add success toast
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO add error toast
                    }
                });
            }
        });

        switchLock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String actionName;
                final boolean auxChecked = isChecked;
                if (isChecked)
                    actionName = "lock";
                else
                    actionName = "unlock";
                requestTag = ApiURLs.getInstance(context).executeAction(dev, actionName, new ArrayList(), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (auxChecked)
                            switchOpen.setText(R.string.lock);
                        else
                            switchOpen.setText(R.string.unlock);
                        // TODO add success toast
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO add error toast
                    }
                });
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        ApiURLs.getInstance(context).cancelRequest(requestTag);
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

package com.grupo7.hci.smarthome.smarthome;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BlindsActivity extends AppCompatActivity {

    private String requestTag;
    private Device dev;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blinds);

        context = this.getApplicationContext();

        final Switch switchOpen = (Switch) findViewById(R.id.switch_blinds_open);

        if (switchOpen != null) {
            requestTag = ApiURLs.getInstance(context).executeAction(dev, "getState", new ArrayList(), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject result = response.getJSONObject("result");
                        String status = result.getString("status");
                        if (status == "opened" || status == "opening") {
                            switchOpen.setText(R.string.open);
                            switchOpen.setChecked(true);
                        } else {
                            switchOpen.setText(R.string.close);
                        }
                        // TODO get response data to set switch
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
                        actionName = "up";
                    else
                        actionName = "down";
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
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        ApiURLs.getInstance(context).cancelRequest(requestTag);
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

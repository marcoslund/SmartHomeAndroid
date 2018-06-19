package com.grupo7.hci.smarthome.smarthome;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BlindsActivity extends Fragment {

    private String requestTag;
    private Device dev;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        return inflater.inflate(R.layout.activity_blinds, container, false);
    }

    @Override
    public void onStop() {
        super.onStop();
        ApiURLs.getInstance(context).cancelRequest(requestTag);
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            String deviceId = getArguments().getString("deviceId");
            String typeId = getArguments().getString("typeId");
            String name = getArguments().getString("name");
            String meta = getArguments().getString("meta");
            dev = new Device(deviceId, typeId, name, meta);
            updateDeviceView();
        }
    }

    public void updateDeviceView() {

        final Switch switchOpen = (Switch) getView().findViewById(R.id.switch_blinds_open);

        if (switchOpen != null) {
            /*requestTag = ApiURLs.getInstance(context).executeAction(dev, "getState", new ArrayList(), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("h", response.toString());
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
                        Log.d("hola", "ERROR FATAL");
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
            });*/

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
                            Log.d("", response.toString());
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

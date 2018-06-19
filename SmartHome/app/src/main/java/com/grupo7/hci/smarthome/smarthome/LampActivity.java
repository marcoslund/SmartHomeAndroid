package com.grupo7.hci.smarthome.smarthome;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LampActivity extends AppCompatActivity {

    private String requestTag;
    private Device dev;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp);

        context = this.getApplicationContext();

        final Switch switchOn = (Switch) findViewById(R.id.switch_lamp_on);
        final SeekBar brightnessSeek = (SeekBar) findViewById(R.id.seekBar_lamp_brightness);

        requestTag = ApiURLs.getInstance(context).executeAction(dev, "getState", new ArrayList(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject result = response.getJSONObject("result");
                    String status = result.getString("status");
                    int brightness  = result.getInt("brightness");
                    if (switchOn != null) {
                        if (status == "on") {
                            switchOn.setText(R.string.on);
                            switchOn.setChecked(true);
                        } else {
                            switchOn.setText(R.string.off);
                        }
                    }
                    if(brightnessSeek != null) {
                        brightnessSeek.setMax(100);
                        brightnessSeek.setProgress(brightness);
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

        if (switchOn != null) {
            switchOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String actionName;
                    final boolean auxChecked = isChecked;
                    if (isChecked)
                        actionName = "turnOn";
                    else
                        actionName = "turnOff";
                    requestTag = ApiURLs.getInstance(context).executeAction(dev, actionName, new ArrayList(), new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            if (auxChecked)
                                switchOn.setText(R.string.on);
                            else
                                switchOn.setText(R.string.off);
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

        if (brightnessSeek != null) {
            brightnessSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onProgressChanged(SeekBar seekBar, final int progress, boolean fromUser) {
                    ArrayList brightness = new ArrayList();
                    brightness.add(progress);
                    requestTag = ApiURLs.getInstance(context).executeAction(dev, "setBrightness", brightness, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
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

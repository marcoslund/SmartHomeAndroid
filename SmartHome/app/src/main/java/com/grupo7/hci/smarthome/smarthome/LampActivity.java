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
                Toast.makeText(context, R.string.init_error_msg, Toast.LENGTH_LONG).show();
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
                            Toast.makeText(context, R.string.action_msg_s, Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, R.string.action_msg_f, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        }

        if (brightnessSeek != null) {
            brightnessSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onProgressChanged(SeekBar seekBar, final int progress, boolean fromUser) {
                    ArrayList brightness = new ArrayList();
                    brightness.add(progress);
                    requestTag = ApiURLs.getInstance(context).executeAction(dev, "setBrightness", brightness, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(context, R.string.action_msg_s, Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, R.string.action_msg_f, Toast.LENGTH_LONG).show();
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

}

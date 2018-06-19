package com.grupo7.hci.smarthome.smarthome;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OvenActivity extends AppCompatActivity {

    private String requestTag;
    private Device dev;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oven);

        context = this.getApplicationContext();

        final Switch switchOn = findViewById(R.id.switch_oven_on);
        final SeekBar temperatureSeek = findViewById(R.id.seekBar_oven_temp);
        final RadioGroup radioGroupHeat = findViewById(R.id.radioGroup_oven_heat);
        final RadioGroup radioGroupGrill = findViewById(R.id.radioGroup_oven_grill);
        final RadioGroup radioGroupConvection = findViewById(R.id.radioGroup_oven_convection);

        if (temperatureSeek != null) {
            temperatureSeek.setMax(230 - 90);
        }

        requestTag = ApiURLs.getInstance(context).executeAction(dev, "getState", new ArrayList(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject result = response.getJSONObject("result");
                    String status = result.getString("status");
                    int temperature  = result.getInt("temperature");
                    String heat = result.getString("heat");
                    String grill = result.getString("grill");
                    String convection = result.getString("convection");
                    if (switchOn != null) {
                        if (status == "on") {
                            switchOn.setText(R.string.on);
                            switchOn.setChecked(true);
                        } else {
                            switchOn.setText(R.string.off);
                        }
                    }
                    if (temperatureSeek != null) {
                        temperatureSeek.setProgress(temperature - 90 );
                    }
                    RadioButton[] selectedButtons = new RadioButton[3];
                    int rb0 = getResources().getIdentifier("radioButton_oven_" + heat.toLowerCase() +"heat",
                            "id", getPackageName());
                    int rb1 = getResources().getIdentifier("radioButton_oven_" + grill +"grill",
                            "id", getPackageName());
                    int rb2 = getResources().getIdentifier("radioButton_oven_" + convection +"convection",
                            "id", getPackageName());
                    selectedButtons[0] = findViewById(rb0);
                    selectedButtons[1] = findViewById(rb1);
                    selectedButtons[2] = findViewById(rb2);
                    for(RadioButton rb : selectedButtons) {
                        if (rb != null)
                            rb.setChecked(true);
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

        if (radioGroupHeat != null) {
            radioGroupHeat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    handleGroupSelection("setHeat", checkedId);
                }
            });
        }

        if (radioGroupGrill != null) {
            radioGroupGrill.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    handleGroupSelection("setGrill", checkedId);
                }
            });
        }

        radioGroupConvection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                handleGroupSelection("setConvection", checkedId);
            }
        });

        temperatureSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ArrayList temperature = new ArrayList();
                temperature.add(progress + 90);
                requestTag = ApiURLs.getInstance(context).executeAction(dev, "setTemperature", temperature, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, R.string.set_temperature_s, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, R.string.set_temperature_f, Toast.LENGTH_LONG).show();
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

    private void handleGroupSelection(String actionName, int checkedId) {
        RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
        ArrayList param = new ArrayList();
        if (checkedRadioButton != null)
            param.add(checkedRadioButton.getText().toString().toLowerCase());
        requestTag = ApiURLs.getInstance(context).executeAction(dev, actionName, param, new Response.Listener<JSONObject>() {
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

}

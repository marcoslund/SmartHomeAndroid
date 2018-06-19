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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ACActivity extends AppCompatActivity {

    private String requestTag;
    private Device dev;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac);

        context = this.getApplicationContext();

        final Switch switchOn = (Switch) findViewById(R.id.switch_ac_on);
        final SeekBar temperatureSeek = findViewById(R.id.seekBar_ac_temp);
        final RadioGroup radioGroupMode = (RadioGroup) findViewById(R.id.radioGroup_ac_mode);
        final RadioGroup radioGroupVSwing = (RadioGroup) findViewById(R.id.radioGroup_vswing);
        final RadioGroup radioGroupHSwing = (RadioGroup) findViewById(R.id.radioGroup_hswing);
        final RadioGroup radioGroupFanSpeed = (RadioGroup) findViewById(R.id.radioGroup_fanspeed);

        if (temperatureSeek != null) {
            temperatureSeek.setMax(20);
        }

        requestTag = ApiURLs.getInstance(context).executeAction(dev, "getState", new ArrayList(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject result = response.getJSONObject("result");
                    String status = result.getString("status");
                    int temperature  = result.getInt("temperature");
                    String mode = result.getString("mode");
                    String vSwing = result.getString("verticalSwing");
                    String hSwing = result.getString("horizontalSwing");
                    String fanSpeed = result.getString("fanSpeed");
                    if (switchOn != null) {
                        if (status == "on") {
                            switchOn.setText(R.string.on);
                            switchOn.setChecked(true);
                        } else {
                            switchOn.setText(R.string.off);
                        }
                    }
                    if (temperatureSeek != null) {
                        temperatureSeek.setProgress(temperature - 18 );
                    }
                    RadioButton[] selectedButtons = new RadioButton[4];
                    int rb0 = getResources().getIdentifier("radioButton_ac_" + mode.toLowerCase(),
                            "id", getPackageName());
                    int rb1 = getResources().getIdentifier("radioButton_ac_" + vSwing.toLowerCase() +"vswing",
                            "id", getPackageName());
                    int rb2 = getResources().getIdentifier("radioButton_ac_" + hSwing.toLowerCase() +"hswing",
                            "id", getPackageName());
                    int rb3 = getResources().getIdentifier("radioButton_ac_" + fanSpeed.toLowerCase() +"fsp",
                            "id", getPackageName());
                    selectedButtons[0] = findViewById(rb0);
                    selectedButtons[1] = findViewById(rb1);
                    selectedButtons[2] = findViewById(rb2);
                    selectedButtons[3] = findViewById(rb3);
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

        if (radioGroupMode != null) {
            radioGroupMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    handleGroupSelection("setMode", checkedId);
                }
            });
        }

        if (radioGroupVSwing != null) {
            radioGroupVSwing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    handleGroupSelection("setVerticalSwing", checkedId);
                }
            });
        }

        if (radioGroupHSwing != null) {
            radioGroupHSwing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    handleGroupSelection("setHorizontalSwing", checkedId);
                }
            });
        }

        if (radioGroupFanSpeed != null) {
            radioGroupFanSpeed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    handleGroupSelection("setFanSpeed", checkedId);
                }
            });
        }

        if (temperatureSeek != null) {
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
                    temperature.add(progress + 18);
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

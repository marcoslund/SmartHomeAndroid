package com.grupo7.hci.smarthome.smarthome;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ACActivity extends Fragment {

    private String requestTag;
    private Device dev;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        return inflater.inflate(R.layout.activity_ac, container, false);
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

        final Switch switchOn = (Switch) getView().findViewById(R.id.switch_ac_on);
        final SeekBar temperatureSeek = getView().findViewById(R.id.seekBar_ac_temp);
        final RadioGroup radioGroupMode = (RadioGroup) getView().findViewById(R.id.radioGroup_ac_mode);
        final RadioGroup radioGroupVSwing = (RadioGroup) getView().findViewById(R.id.radioGroup_vswing);
        final RadioGroup radioGroupHSwing = (RadioGroup) getView().findViewById(R.id.radioGroup_hswing);
        final RadioGroup radioGroupFanSpeed = (RadioGroup) getView().findViewById(R.id.radioGroup_fanspeed);

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
                    selectedButtons[0] = getView().findViewById(rb0);
                    selectedButtons[1] = getView().findViewById(rb1);
                    selectedButtons[2] = getView().findViewById(rb2);
                    selectedButtons[3] = getView().findViewById(rb3);
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

    private void handleGroupSelection(String actionName, int checkedId) {
        RadioButton checkedRadioButton = (RadioButton) getView().findViewById(checkedId);
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

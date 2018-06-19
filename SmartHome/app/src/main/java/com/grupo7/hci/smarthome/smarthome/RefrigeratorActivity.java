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

public class RefrigeratorActivity extends Fragment {

    private String requestTag;
    private Device dev;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        return inflater.inflate(R.layout.activity_refrigerator, container, false);
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

        final SeekBar temperatureSeek = (SeekBar) getView().findViewById(R.id.seekBar_refrigerator_temperature);
        final SeekBar freezerSeek = (SeekBar) getView().findViewById(R.id.seekBar_refrigerator_freezer);
        final RadioGroup radioGroupMode = (RadioGroup) getView().findViewById(R.id.radioGroup_refrigerator_mode);

        if (temperatureSeek != null) {
            temperatureSeek.setMax(6);
        }
        if (freezerSeek != null) {
            freezerSeek.setMax(12);
        }

        requestTag = ApiURLs.getInstance(context).executeAction(dev, "getState", new ArrayList(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject result = response.getJSONObject("result");
                    int temperature  = result.getInt("temperature");
                    int freezerTemperatire = result.getInt("freezerTemperature");
                    String mode = result.getString("mode");
                    if (temperatureSeek != null) {
                        temperatureSeek.setProgress(temperature - 2 );
                    }
                    if (freezerSeek != null) {
                        freezerSeek.setProgress(freezerTemperatire + 20 );
                    }
                    RadioButton[] selectedButtons = new RadioButton[1];
                    int rb0 = getResources().getIdentifier("radioButton_refrigerator_" + mode.toLowerCase() + "mode",
                            "id", getPackageName());
                    selectedButtons[0] = getView().findViewById(rb0);
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

        if (radioGroupMode != null) {
            radioGroupMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton checkedRadioButton = (RadioButton) getView().findViewById(checkedId);
                    ArrayList param = new ArrayList();
                    if (checkedRadioButton != null)
                        param.add(checkedRadioButton.getText().toString().toLowerCase());
                    requestTag = ApiURLs.getInstance(context).executeAction(dev, "setMode", param, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(context, R.string.set_mode_s, Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, R.string.set_mode_f, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        }

        if (temperatureSeek != null) {
            temperatureSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    ArrayList temperature = new ArrayList();
                    temperature.add(progress + 2);
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

        if (freezerSeek != null) {
            freezerSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    ArrayList temperature = new ArrayList();
                    temperature.add(progress - 20);
                    requestTag = ApiURLs.getInstance(context).executeAction(dev, "setFreezerTemperature", temperature, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(context, R.string.set_ftemperature_s, Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, R.string.set_ftemperature_f, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        }
    }

}

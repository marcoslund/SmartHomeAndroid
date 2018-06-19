package com.grupo7.hci.smarthome.smarthome;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CreateDeviceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_device);

        Context context = getApplicationContext();
        final ApiURLs api = ApiURLs.getInstance(context);

        final EditText deviceNameField = findViewById(R.id.add_device_name);
        final Spinner categorySpinner = findViewById(R.id.add_device_spinner);
        final Button cancel = findViewById(R.id.add_device_cancel_button);
        final Button save = findViewById(R.id.add_device_save_button);

        JSONArray categoryJson;
        final List<String> categoryIdList = new ArrayList<>();
        List<String> categoryList = new ArrayList<>();
        final StringBuilder textInput= new StringBuilder();
        final StringBuilder selectedCategoryId = new StringBuilder();

        try {
            categoryJson = new DeviceType().getDeviceTypes();
            for(int i = 0; i < categoryJson.length(); i++) {
                JSONObject jsonElement = categoryJson.getJSONObject(i);
                categoryIdList.add(jsonElement.getString("id"));
                int j = jsonElement.getInt("name");
                categoryList.add(getString(j));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    R.layout.support_simple_spinner_dropdown_item, categoryList);
            categorySpinner.setAdapter(adapter);
            categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String str = categoryIdList.get(position);
                    selectedCategoryId.replace(0, str.length(), str);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {

                }

            });
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Editable input = deviceNameField.getText();
                    if(input == null || input.toString().equals("")) {
                        Toast.makeText(getApplicationContext(), getString(R.string.no_name_error),
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                    final Device dev = new Device(selectedCategoryId.toString(), input.toString(), "");
                    api.addDevice(dev, new Response.Listener<Device>() {
                        @Override
                        public void onResponse(Device response) {
                            dev.setId(response.getId());
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            startActivity(intent);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                }
            });

        }
        catch(Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), String.valueOf("Could not get categories"),
                    Toast.LENGTH_LONG).show();
        }


    }
}

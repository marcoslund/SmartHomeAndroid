package com.grupo7.hci.smarthome.smarthome;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.grupo7.hci.smarthome.smarthome.Classes.ApiURLs;
import com.grupo7.hci.smarthome.smarthome.Classes.Device;

import org.json.JSONObject;

public class DevicesTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_devices, container, false);

        /*RequestQueue requestQueue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        ApiURLs urls = new ApiURLs();
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, urls.getAllDevicesURL(), new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Success", "Successfull get request");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "Failed request");
                        error.printStackTrace();
                    }
        });
        requestQueue.add(jsObjRequest);

        /*for(Device device : devices) {
            Button deviceBtn = new Button(getActivity());
            deviceBtn.setBackgroundResource(R.drawable.devices_button);
            deviceBtn.setText(device.getName());
            if(device.getTypeId().equals(DEVICE TYPE ID ACA)) {
                deviceBtn.setCompoundDrawablesWithIntrinsicBounds();
            }
            Drawable icon= getContext().getResources().getDrawable( R.drawable.image);
            button.setCompoundDrawablesWithIntrinsicBounds( icon, null, null, null );
        }*/

        return rootView;
    }



}

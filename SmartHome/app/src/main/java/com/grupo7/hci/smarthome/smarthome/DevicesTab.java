package com.grupo7.hci.smarthome.smarthome;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.grupo7.hci.smarthome.smarthome.Classes.ApiURLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DevicesTab extends Fragment {
    LinearLayout parent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_devices, container, false);

        RequestQueue requestQueue = VolleySingleton.getInstance(this.getContext()).getRequestQueue();
        ApiURLs urls = new ApiURLs();
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, urls.getAllDevicesURL(), new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Success", "Successful get request" + response.toString());
                        try {
                            JSONArray mJsonArrayProperty = response.getJSONArray("devices");
                            for (int i = 0; i < mJsonArrayProperty.length(); i++) {
                                JSONObject mJsonObjectProperty = mJsonArrayProperty.getJSONObject(i);

                                String id = mJsonObjectProperty.getString("id");
                                String name = mJsonObjectProperty.getString("name");
                                String typeId = mJsonObjectProperty.getString("typeId");
                                Log.d("name", "Id: " + id);
                                Log.d("name", "Name: " + name);
                                Log.d("name", "Type Id: " + typeId);

                                Button deviceBtn = new Button(getActivity());

                                //deviceBtn.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.devices_button, null));
                                //deviceBtn.setBackgroundResource(R.drawable.devices_button);
                                deviceBtn.setText(name);
                                //deviceBtn.setId(Integer.parseInt(id));
                                Drawable image;
                                Bitmap bitmap;
                                if(typeId.equals("li6cbv5sdlatti0j")) {
                                    image = ResourcesCompat.getDrawable(getResources(), R.drawable.ac_icon, null);
                                    bitmap = ((BitmapDrawable) image).getBitmap();
                                    image = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 200, 200, true));
                                } else if(typeId.equals("eu0v2xgprrhhg41g")) {
                                    image = ResourcesCompat.getDrawable(getResources(), R.drawable.blinds_icon, null);
                                    bitmap = ((BitmapDrawable) image).getBitmap();
                                    image = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 200, 200, true));
                                } else if(typeId.equals("lsf78ly0eqrjbz91")) {
                                    image = ResourcesCompat.getDrawable(getResources(), R.drawable.door_icon, null);
                                    bitmap = ((BitmapDrawable) image).getBitmap();
                                    image = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 200, 200, true));
                                } else if(typeId.equals("go46xmbqeomjrsjr")) {
                                    image = ResourcesCompat.getDrawable(getResources(), R.drawable.lamp_icon, null);
                                    bitmap = ((BitmapDrawable) image).getBitmap();
                                    image = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 200, 200, true));
                                } else if(typeId.equals("im77xxyulpegfmv8")) {
                                    image = ResourcesCompat.getDrawable(getResources(), R.drawable.oven_icon, null);
                                    bitmap = ((BitmapDrawable) image).getBitmap();
                                    image = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 200, 200, true));
                                } else /*if(typeId.equals("rnizejqr2di0okho"))*/ {
                                    image = ResourcesCompat.getDrawable(getResources(), R.drawable.refrigerator_icon, null);
                                    bitmap = ((BitmapDrawable) image).getBitmap();
                                    image = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 200, 200, true));
                                }
                                deviceBtn.setCompoundDrawablesWithIntrinsicBounds(image, null, null, null);
                                parent = (LinearLayout) getActivity().findViewById(R.id.l1_parent);
                                parent.addView(deviceBtn);
                            }
                        } catch (JSONException e) {
                            Log.e("MYAPP", "unexpected JSON exception", e);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "Failed request");
                        error.printStackTrace();
                    }
        });
        requestQueue.add(jsObjRequest);

        /*
        }*/

        return rootView;
    }



}

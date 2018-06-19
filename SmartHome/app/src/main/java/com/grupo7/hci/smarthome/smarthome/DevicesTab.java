package com.grupo7.hci.smarthome.smarthome;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
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

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

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

                                final String id = mJsonObjectProperty.getString("id");
                                final String name = mJsonObjectProperty.getString("name");
                                final String typeId = mJsonObjectProperty.getString("typeId");
                                final String meta = mJsonObjectProperty.getString("meta");
                                Log.d("id", "Id: " + id);
                                Log.d("typeId", "Type Id: " + typeId);
                                Log.d("name", "Name: " + name);
                                Log.d("meta", "Meta: " + meta);
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

                                    deviceBtn.setOnClickListener(new OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            startActivity(new Intent(getActivity(), ACActivity.class));
                                        }
                                    });

                                } else if(typeId.equals("eu0v2xgprrhhg41g")) {

                                    deviceBtn.setOnClickListener(new OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            onDeviceSelected(id, typeId, name, meta);
                                        }
                                    });

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

        return rootView;
    }

    public void onDeviceSelected(String id, String typeId, String name, String meta) {
        Log.d("hola", "Clicked " + id + " " + typeId + " " + name + " " + meta);
        if(typeId.equals("li6cbv5sdlatti0j")) {

        } else if(typeId.equals("eu0v2xgprrhhg41g")) { // Blinds
            BlindsActivity blindsFrag = new BlindsActivity();
            Bundle args = new Bundle();
            args.putString("deviceId", id);
            args.putString("typeId", typeId);
            args.putString("name", name);
            args.putString("meta", meta);
            blindsFrag.setArguments(args);
            //FragmentTransaction transaction = getFragmentManager().beginTransaction()
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .show(blindsFrag)
                    .commit();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            //transaction.replace(R.id.devices, blindsFrag);
            //transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            //transaction.addToBackStack(null);
            // Commit the transaction
            //transaction.commit();
        } else if(typeId.equals("lsf78ly0eqrjbz91")) {

        } else if(typeId.equals("go46xmbqeomjrsjr")) {

        } else if(typeId.equals("im77xxyulpegfmv8")) {

        } else /*if(typeId.equals("rnizejqr2di0okho"))*/ {

        }

    }

}

package com.grupo7.hci.smarthome.smarthome;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.provider.Settings;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DoorActivity extends AppCompatActivity {

    private String requestTag;
    private Device dev;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);

        context = this.getApplicationContext();

        final Switch switchOpen = (Switch) findViewById(R.id.switch_door_open);
        final Switch switchLock = (Switch) findViewById(R.id.switch_door_lock);

        Intent notificationIntent = new Intent(context, DoorActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(DoorActivity.class);
        stackBuilder.addNextIntent(notificationIntent);
        final PendingIntent contentIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        requestTag = ApiURLs.getInstance(context).executeAction(dev, "getState", new ArrayList(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject result = response.getJSONObject("result");
                    String status = result.getString("status");
                    String lock = result.getString("lock");
                    if (switchOpen != null) {
                        if (status == "opened" || status == "opening") {
                            switchOpen.setText(R.string.open);
                            switchOpen.setChecked(true);
                            Notification notification = new Notification.Builder(context)
                                    .setContentTitle(getResources().getString(R.string.notifTitle))
                                    .setContentText(getResources().getString(R.string.notifBody))
                                    .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_info_details))
                                    .setSmallIcon(android.R.drawable.ic_menu_info_details)
                                    .setContentIntent(contentIntent).build();

                            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            notificationManager.notify(0, notification);
                        } else {
                            switchOpen.setText(R.string.close);
                        }
                    }
                    if(switchLock != null) {
                        if (status == "locked") {
                            switchLock.setText(R.string.lock);
                            switchLock.setChecked(true);
                        } else {
                            switchLock.setText(R.string.unlock);
                        }
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


        switchOpen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String actionName;
                final boolean auxChecked = isChecked;
                if (isChecked)
                    actionName = "open";
                else
                    actionName = "close";
                requestTag = ApiURLs.getInstance(context).executeAction(dev, actionName, new ArrayList(), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (auxChecked) {
                            switchOpen.setText(R.string.open);
                            Notification notification = new Notification.Builder(context)
                                    .setContentTitle(getResources().getString(R.string.notifTitle))
                                    .setContentText(getResources().getString(R.string.notifBody))
                                    .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_info_details))
                                    .setSmallIcon(android.R.drawable.ic_menu_info_details)
                                    .setContentIntent(contentIntent).build();

                            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            notificationManager.notify(0, notification);
                        } else {
                            switchOpen.setText(R.string.close);
                        }
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

        switchLock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String actionName;
                final boolean auxChecked = isChecked;
                if (isChecked)
                    actionName = "lock";
                else
                    actionName = "unlock";
                requestTag = ApiURLs.getInstance(context).executeAction(dev, actionName, new ArrayList(), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (auxChecked)
                            switchOpen.setText(R.string.lock);
                        else
                            switchOpen.setText(R.string.unlock);
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


    @Override
    protected void onStop() {
        super.onStop();
        ApiURLs.getInstance(context).cancelRequest(requestTag);
    }


}

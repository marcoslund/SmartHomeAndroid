package com.grupo7.hci.smarthome.smarthome;

import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.app.AlarmManager;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DoorActivity extends Fragment {

    private String requestTag;
    private Device dev;
    private Context context;
    private NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    private AlarmManager alarmManager;
    private PendingIntent alarmNotificationReceiverPendingIntent;
    private PendingIntent contentIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        return inflater.inflate(R.layout.activity_door, container, false);
    }

    @Override
    public void onStop() {
        super.onStop();
        ApiURLs.getInstance(context).cancelRequest(requestTag);
        alarmManager.cancel(alarmNotificationReceiverPendingIntent);
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
        final Switch switchOpen = (Switch) getView().findViewById(R.id.switch_door_open);
        final Switch switchLock = (Switch) getView().findViewById(R.id.switch_door_lock);

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
                Toast.makeText(context, R.string.init_error_msg, Toast.LENGTH_LONG).show();
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
                            scheduleNotification(60000);
                        } else {
                            switchOpen.setText(R.string.close);
                        }
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

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);

        context = this.getApplicationContext();

        final String deviceId = getIntent().getStringExtra("deviceId");
        if (deviceId != null) {
            requestTag = ApiURLs.getInstance(context).getDevice(deviceId, new Response.Listener<Device>() {
                @Override
                public void onResponse(Device response) {
                    dev = new Device(deviceId, response.getName(), response.getTypeId(), response.getMeta());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO toast init
                    error.printStackTrace();
                }
            });
        }


    }*/

    private void scheduleNotification(int delay) {
        Intent finalNotificationIntent = new Intent(context, DoorActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(DoorActivity.class);
        stackBuilder.addNextIntent(finalNotificationIntent);
        finalNotificationIntent.putExtra("deviceId", dev.getId());
        contentIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(context)
                .setContentTitle(getResources().getString(R.string.notifTitle))
                .setContentText(getResources().getString(R.string.notifBody))
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_info_details))
                .setSmallIcon(android.R.drawable.ic_menu_info_details)
                .setContentIntent(contentIntent).build();

        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent(getContext(), NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }
}

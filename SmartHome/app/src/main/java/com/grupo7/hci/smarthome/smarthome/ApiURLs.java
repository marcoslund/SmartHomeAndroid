package com.grupo7.hci.smarthome.smarthome;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ApiURLs {

    public String baseUrl = "http://10.0.2.2:8080/api/";

    private static ApiURLs instance;
    private static RequestQueue requestQueue;

    private ApiURLs(Context context) {
        this.requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
    }

    public static synchronized ApiURLs getInstance(Context context) {
        if (instance == null) {
            instance = new ApiURLs(context);
        }
        return instance;
    }

    public String getAllRoutinesURL() { return baseUrl + "routines/"; }
    public String getAllDevicesURL() { return baseUrl + "devices/"; }


    public String addDevice(Device dev, Response.Listener<Device> listener, Response.ErrorListener errorListener) {
        String url = baseUrl + "devices";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Device, Device> request =
                new GsonRequest<Device, Device>(Request.Method.POST, url, dev, "device", Device.class, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        return uuid;
    }

    public String modifyDevice(Device dev, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        String url = baseUrl + "devices/" + dev.getId();
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Device, Boolean> request =
                new GsonRequest<Device, Boolean>(Request.Method.PUT, url, dev, "result", Boolean.class, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String deleteDevice(String id, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        String url = baseUrl + "devices/" + id;
        GsonRequest<Object, Boolean> request =
                new GsonRequest<Object, Boolean>(Request.Method.DELETE, url, null, "result", Boolean.class, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String getDevice(String id, Response.Listener<Device> listener, Response.ErrorListener errorListener) {
        String url = baseUrl + "devices/" + id;
        GsonRequest<Object, Device> request =
                new GsonRequest<Object, Device>(Request.Method.GET, url, null, "device", Device.class, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String executeAction(Device dev, String actionName, ArrayList params, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        String url = baseUrl + "devices/" + dev.getId() + "/" + actionName;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        GsonRequest<ArrayList, Boolean> request =
                new GsonRequest<ArrayList, Boolean>(Request.Method.PUT, url, params, "result", Boolean.class, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }


    public String addRoutine(Routine rou, Response.Listener<Routine> listener, Response.ErrorListener errorListener) {
        String url = baseUrl + "routines";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Routine, Routine> request =
                new GsonRequest<Routine, Routine>(Request.Method.POST, url, rou, "routine", Routine.class, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);
        return uuid;
    }

    public String modifyRoutine(Routine rou, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        String url = baseUrl + "routines/" + rou.getId();
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        GsonRequest<Routine, Boolean> request =
                new GsonRequest<Routine, Boolean>(Request.Method.PUT, url, rou, "result", Boolean.class, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String deleteRoutine(String id, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        String url = baseUrl + "routines/" + id;
        GsonRequest<Object, Boolean> request =
                new GsonRequest<Object, Boolean>(Request.Method.DELETE, url, null, "result", Boolean.class, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String getRoutine(String id, Response.Listener<Routine> listener, Response.ErrorListener errorListener) {
        String url = baseUrl + "routines/" + id;
        GsonRequest<Object, Routine> request =
                new GsonRequest<Object, Routine>(Request.Method.GET, url, null, "routine", Routine.class, null, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }

    public String executeRoutine(Routine rou, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        String url = baseUrl + "routines/" + rou.getId() + "/execute";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        GsonRequest<ArrayList, Boolean> request =
                new GsonRequest<ArrayList, Boolean>(Request.Method.PUT, url, rou.getActions(), "result", Boolean.class, headers, listener, errorListener);
        String uuid = UUID.randomUUID().toString();
        request.setTag(uuid);
        requestQueue.add(request);

        return uuid;
    }


    public void cancelRequest(String uuid) {
        if ((uuid != null) && (requestQueue != null)) {
            requestQueue.cancelAll(uuid);
        }
    }
}

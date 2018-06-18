package com.grupo7.hci.smarthome.smarthome;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceType {
    private JSONArray deviceTypes;

    public DeviceType() throws  JSONException{
        deviceTypes = new JSONArray();
        JSONObject ac = new JSONObject();
        try {
            ac.put("id", "li6cbv5sdlatti0j");
            ac.put("name", R.string.air_conditioner);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject blind = new JSONObject();
        try {
            blind.put("id", "eu0v2xgprrhhg41g");
            blind.put("name", R.string.blinds);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject lamp = new JSONObject();
        try {
            lamp.put("id", "go46xmbqeomjrsjr");
            lamp.put("name", R.string.lamp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject oven = new JSONObject();
        try {
            oven.put("id", "im77xxyulpegfmv8");
            oven.put("name", R.string.oven);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject door = new JSONObject();
        try {
            door.put("id", "lsf78ly0eqrjbz91");
            door.put("name", R.string.door);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject refri = new JSONObject();
        try {
            refri.put("id", "rnizejqr2di0okho");
            refri.put("name", R.string.refrigerator);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.deviceTypes.put(ac);
        this.deviceTypes.put(blind);
        this.deviceTypes.put(lamp);
        this.deviceTypes.put(oven);
        this.deviceTypes.put(door);
        this.deviceTypes.put(refri);
    }

    public JSONArray getDeviceTypes() { return this.deviceTypes; }
}

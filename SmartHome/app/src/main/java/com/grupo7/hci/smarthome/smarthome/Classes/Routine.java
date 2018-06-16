package com.grupo7.hci.smarthome.smarthome.Classes;

import org.json.JSONObject;

public class Routine {
    private String id;
    private JSONObject actions;
    private String name;
    private JSONObject meta;

    public Routine(String id, JSONObject actions, String name, JSONObject meta) {
        this.id = id;
        this.actions = actions;
        this.name = name;
        this.meta = meta;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public JSONObject getActions() { return actions; }
    public void setActions(JSONObject actions) { this.actions = actions; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public JSONObject getMeta() { return meta; }
    public void setMeta(JSONObject meta) { this.meta = meta; }
}

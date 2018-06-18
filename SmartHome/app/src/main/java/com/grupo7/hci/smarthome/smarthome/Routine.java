package com.grupo7.hci.smarthome.smarthome;

import org.json.JSONObject;

public class Routine {
    private String id;
    private JSONObject actions;
    private String name;
    private String meta;

    public Routine(String id, JSONObject actions, String name, String meta) {
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

    public String getMeta() { return meta; }
    public void setMeta(String meta) { this.meta = meta; }

    @Override
    public String toString() {
        return (this.id == null) ?
                String.format("%s - %s", this.getName(), this.getMeta()) :
                String.format("%s - %s - %s", this.getId(), this.getName(), this.getMeta());
    }
}

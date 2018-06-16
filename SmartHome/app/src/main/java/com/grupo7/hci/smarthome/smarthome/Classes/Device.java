package com.grupo7.hci.smarthome.smarthome.Classes;

import org.json.JSONObject;

public class Device {
    private String id;
    private String typeId;
    private String name;
    private JSONObject meta;

    public Device(String id, String typeId, String name, JSONObject meta) {
        this.id = id;
        this.typeId = typeId;
        this.name = name;
        this.meta = meta;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTypeId() { return typeId; }
    public void setTypeId(String typeId) { this.typeId = typeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public JSONObject getMeta() { return meta; }
    public void setMeta(JSONObject meta) { this.meta = meta; }
}

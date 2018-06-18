package com.grupo7.hci.smarthome.smarthome;

import org.json.JSONObject;

public class Device {
    private String id;
    private String typeId;
    private String name;
    private String meta;

    public Device(String id, String typeId, String name, String meta) {
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

    public String getMeta() { return meta; }
    public void setMeta(String meta) { this.meta = meta; }

    @Override
    public String toString() {
        return (this.id == null) ?
                String.format("%s - %s", this.getName(), this.getMeta()) :
                String.format("%s - %s - %s", this.getId(), this.getName(), this.getMeta());
    }
}

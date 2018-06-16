package com.grupo7.hci.smarthome.smarthome.Classes;

public class ApiURLs {
    public String baseUrl = "http://10.0.2.2:8080/api/";

    public String getRoutineByIdURL(String id) { return baseUrl + "routines/" + id; }
    public String getDeviceByIdURL(String id) { return baseUrl + "devices/" + id; }
    public String getDeviceTypeByIdURL(String id) { return baseUrl + "devicetypes/" + id; }
    public String getAllRoutinesURL() { return baseUrl + "routines/"; }
    public String getAllDevicesURL() { return baseUrl + "devices/"; }
    public String getAllDeviceTypesURL() { return baseUrl + "devicetypes/"; }

    public String deleteRoutineURL(String id) { return baseUrl + "routines/" + id; }
    public String deleteDeviceURL(String id) { return baseUrl + "devices/" + id; }

    public String postRoutineURL() { return baseUrl + "routines/"; }
    public String postDeviceURL() { return baseUrl + "devices/"; }

    public String modifyRoutineURL(String id) { return baseUrl + "routines/" + id; }
    public String modifyDeviceURL(String id) { return baseUrl + "devices/" + id; }
    public String executeDeviceActionURL(String id, String actionName) { return baseUrl + "devices/" + id + "/" + actionName; }
    public String executeActionsURL(String id) { return baseUrl + "routines/" + id + "/execute"; }
}

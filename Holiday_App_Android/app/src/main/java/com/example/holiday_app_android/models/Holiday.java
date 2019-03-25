package com.example.holiday_app_android.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Holiday {
    private String ID;
    private String Name;
    private String Date;

    public Holiday(JSONObject object)
    {
        try {
            this.ID = object.getString("id");
            this.Name = object.getString("name");
            this.Date = object.getString("date");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Holiday(String Name, String Date)
    {
        this.Name = Name;
        this.Date = Date;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}

package com.example.holiday_app_android.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Holiday {
    private String Name;
    private String Date;

    @SuppressWarnings("unused")
    public Holiday(JSONObject object)
    {
        try {
            String ID = object.getString("id");
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

    public String getName() {
        return Name;
    }

    public String getDate() {
        return Date;
    }

}

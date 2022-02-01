package com.personal.search_list;

import com.google.gson.annotations.SerializedName;

public class DutyName {

    @SerializedName("duty_name_id") private int duty_name_id;
    @SerializedName("duty_name") private String duty_name;

    public int getDuty_name_id() {
        return duty_name_id;
    }

    public String getDuty_name() {
        return duty_name;
    }
}

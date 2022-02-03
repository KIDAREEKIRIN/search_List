package com.personal.search_list;

import com.google.gson.annotations.SerializedName;

public class DutyName {

    @SerializedName("duty_name_id") private int duty_name_id;
    @SerializedName("duty_name") private String duty_name;
    private boolean isChecked = false;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getDuty_name_id() {
        return duty_name_id;
    }

    public String getDuty_name() {
        return duty_name;
    }

    public void setDuty_name_id(int duty_name_id) {
        this.duty_name_id = duty_name_id;
    }

    public void setDuty_name(String duty_name) {
        this.duty_name = duty_name;
    }
}

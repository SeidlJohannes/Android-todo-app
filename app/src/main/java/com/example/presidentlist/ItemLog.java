package com.example.presidentlist;

import java.util.List;

public class ItemLog {

    private int id;
    private String changedName;
    private String changedValue;
    private String changedURL;

    public ItemLog(int id, String changedName, String changedDate, String changedURL) {
        this.id = id;
        this.changedName = changedName;
        this.changedValue = changedDate;
        this.changedURL = changedURL;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChangedName() {
        return changedName;
    }

    public void setChangedName(String changedName) {
        this.changedName = changedName;
    }

    public String getChangedValue() {
        return changedValue;
    }

    public void setChangedValue(String changedValue) {
        this.changedValue = changedValue;
    }

    public String getChangedURL() {
        return changedURL;
    }

    public void setChangedURL(String changedURL) {
        this.changedURL = changedURL;
    }
}


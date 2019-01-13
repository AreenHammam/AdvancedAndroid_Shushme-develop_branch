package com.example.android.NavSite;

/**
 * Created by hamma on 12-Jan-19.
 */

public class PlaceObjDB {
    private String id;
    private String summary;

    public PlaceObjDB(String id,String summary) {
        this.id=id;
        this.summary=summary;
    }

    public String getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "com.example.android.NavSite.PlaceObjDB{" +
                "id='" + id + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}

package com.example.phishchecker.data;

import com.google.gson.annotations.SerializedName;

public class ValidData {
    @SerializedName("in_database")
    public String indb;
    @SerializedName("url")
    public String url;
    public String format;
    public String verified;
    public String valid;

    public ValidData(String url,String format) {
        this.url = url;
        this.format=format;
    }

    public String getUrl() {
        return url;
    }

    public String getFormat() {
        return format;
    }

    public String getIndb() {
        return indb;
    }

    public String getVerified() {
        return verified;
    }

    public String getValid() {
        return valid;
    }
}

package com.example.phishchecker.data;

import com.google.gson.annotations.SerializedName;

public class UrlData {
    @SerializedName("url")
    public String url;
    @SerializedName("format")
    public String format;

    public UrlData(String url, String format) {
        this.url = url;
        this.format = format;
    }

    public String getUrl() {
        return url;
    }

    public String getFormat() {
        return format;
    }
}

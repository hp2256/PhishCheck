package com.example.phishchecker.network;

import com.example.phishchecker.data.UrlData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    // @FormUrlEncoded
    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    @POST("checkurl")
    Call<String> fetchU(@Body UrlData url);

    /*
        @FormUrlEncoded
        @POST("checkurl")
        Call<ValidData>fetchUr(@FieldMap Map<String,String> fields);
    */
    @FormUrlEncoded
    @POST("checkurl")
    Call<String> fetchUrl(@Field("url") String url,
                          @Field("format") String format);
}

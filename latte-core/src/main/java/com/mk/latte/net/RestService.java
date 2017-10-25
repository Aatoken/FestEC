package com.mk.latte.net;

import java.util.ResourceBundle;
import java.util.WeakHashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by lenovo on 2017/10/17.
 */

public interface RestService {



    @GET
    Call<String> get(@Url String url,@QueryMap WeakHashMap<String, Object> params);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap WeakHashMap<String,Object> params);

    @POST
    Call<String> postRaw(@Url String url, @Body RequestBody body);

    @FormUrlEncoded
    @POST
    Call<String> put(@Url String url, @FieldMap WeakHashMap<String,Object> params);

    @PUT
    Call<String> putRaw(@Url String url, @Body RequestBody body);


    @GET
    Call<String> delete(@Url String url,@QueryMap WeakHashMap<String, Object> params);

    /**
     * Streaming 边下载边写入避免内存溢出
     * @param url
     * @param params
     * @return
     */
    @Streaming
    @GET
    Call<ResourceBundle> download(@Url String url, @QueryMap WeakHashMap<String,Object> params);

    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);


}























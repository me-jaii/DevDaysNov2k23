package com.example.devdays2k23;

import com.example.devdays2k23.struct.ResponseModel;
import com.example.devdays2k23.struct.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("top-headlines")
    Call<ResponseModel> getLatest(
            @Query("language") String language,
            @Query("category") String category,
            @Query("q") String q,
            @Query("apiKey") String apiKey);

}

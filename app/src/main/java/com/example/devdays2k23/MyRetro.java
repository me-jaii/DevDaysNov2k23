package com.example.devdays2k23;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetro {
    static Retrofit retro;
    static Api api;
    static boolean isOk;
    public static final String BASE_URL = "https://newsapi.org/v2/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetro() {
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void setRetro(Retrofit retro) {
        MyRetro.retro = retro;
    }

    public static Api getApi() {
        return api;
    }

    public static void setApi(Api api) {
        MyRetro.api = api;
    }

    public static boolean isIsOk() {
        return isOk;
    }

    public static void setIsOk(boolean isOk) {
        MyRetro.isOk = isOk;
    }
}

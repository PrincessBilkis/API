package com;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APICLIENT

{
    private static  String Baseuri="http://192.168.43.151/UserApi/";
    private static APICLIENT mInstance;
    private Retrofit retrofit;

    private APICLIENT(){
        retrofit=new Retrofit.Builder()
                .baseUrl(Baseuri)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static  synchronized  APICLIENT getInstance(){
        if (mInstance==null){
            mInstance=new APICLIENT();

        }
        return mInstance;
    }
    public Inter getApi(){
        return retrofit.create(Inter.class);
    }

}

package com.talabto.emitterbyezzat.network;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.talabto.emitterbyezzat.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserClient {
    private static  UserClient userClient=null;
    private Retrofit retrofit;
    private UserApi userApi;

    public UserClient() {
        retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create())
                .build();

        userApi=retrofit.create(UserApi.class);
    }


    public static synchronized UserClient getInstance()
    {
        if (userClient==null)
        {
           userClient= new UserClient();
        }
        return userClient;

    }

    public Call<List<UserModel>> getUsers()
    {
        return userApi.getAllUsers();
    }


}

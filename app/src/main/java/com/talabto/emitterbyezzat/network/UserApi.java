package com.talabto.emitterbyezzat.network;

import androidx.lifecycle.LiveData;

import com.talabto.emitterbyezzat.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApi {
    @GET("users")
    Call<List<UserModel>> getAllUsers();
}

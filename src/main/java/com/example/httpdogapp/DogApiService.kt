package com.example.httpdogapp

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface DogApiService {
    @GET("{code}.jpg")
    fun getDog(@Path("code") code: String): Call<ResponseBody>

    @POST("201.jpg")
    fun postDog(): Call<ResponseBody>

    @PATCH("204.jpg")
    fun patchDog(): Call<ResponseBody>

    @DELETE("404.jpg")
    fun deleteDog(): Call<ResponseBody>
}


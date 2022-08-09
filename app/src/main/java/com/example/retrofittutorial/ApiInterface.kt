package com.example.retrofittutorial

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {

    @GET("posts")
    fun getData() : Call<List<MyDataItem>>

    @POST("user/classes")
    fun addToPlaylist(
        @Header("Content-Type") content_type: String?): Response<MyDataItem?>?


}
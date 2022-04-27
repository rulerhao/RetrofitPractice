package com.rulhouse.retrofitpractice

import retrofit2.Call
import retrofit2.http.GET

interface FakeAPIService {
    //取得單筆資料
    @GET("/posts/1")
    fun getPost(): Call<JsonplaceholderPost>

    //取得單筆資料
    @GET("/posts")
    fun getPosts(): Call<List<JsonplaceholderPost>>
}
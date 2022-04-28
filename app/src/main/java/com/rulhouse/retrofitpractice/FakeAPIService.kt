package com.rulhouse.retrofitpractice

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FakeAPIService {
    //取得單筆資料
    @GET("/posts/1")
    fun getPost(): Call<JsonplaceholderPost>

    //取得單筆資料
    @GET("/posts")
    fun getPosts(): Call<List<JsonplaceholderPost>>

    @POST("posts")
    fun postsData (@Body posts: JsonplaceholderPost): Call<JsonplaceholderPost>
}
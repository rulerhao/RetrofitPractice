package com.rulhouse.retrofitpractice

import retrofit2.Call
import retrofit2.http.GET

interface FakeAPIService {
    @GET("/posts/1") //annotation 註解宣告方式定義 HTTP 連線獲取資料方法與指定API後網址
    fun getPost(): Call<JsonplaceholderPost>
}
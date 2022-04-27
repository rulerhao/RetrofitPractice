package com.rulhouse.retrofitpractice

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GetData {
    fun getSingle() {
        val tag = this::class.simpleName
        // baseUrl: 定義 API 網域網址，即是我們剛剛拆出來的前綴共用的部分
        // ddConverterFactory: 定義要將資料轉成什麼格式
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val fakeAPIService = retrofit.create(FakeAPIService::class.java)

        //宣告 Call 連線服務
        val call: Call<JsonplaceholderPost> = fakeAPIService.getPost()

        //執行連線服務，透過 Callback 來等待回傳成功或失敗的資料
        call.enqueue(
            object : Callback<JsonplaceholderPost?> {
                override fun onResponse(
                    call: Call<JsonplaceholderPost?>,
                    response: Response<JsonplaceholderPost?>
                ) {
                    // 連線成功，透過 getter 取得特定欄位資料
                    Log.d(tag, "id: " + response.body()?.id)
                    Log.d(tag, "title: " + response.body()?.title)
                    Log.d(tag, "body: " + response.body()?.body)
                    Log.d(tag, "userId: " + response.body()?.userId)
                }

                override fun onFailure(call: Call<JsonplaceholderPost?>, t: Throwable) {
                    // 連線失敗，印出錯誤訊息
                    Log.d(tag, "response: $t")
                }
            }
        )
    }

    fun getMultiple() {
        val tag = this::class.simpleName
        // baseUrl: 定義 API 網域網址，即是我們剛剛拆出來的前綴共用的部分
        // ddConverterFactory: 定義要將資料轉成什麼格式
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val fakeAPIService = retrofit.create(FakeAPIService::class.java)

        //宣告 Call 連線服務
        val call: Call<List<JsonplaceholderPost>> = fakeAPIService.getPosts()

        //執行連線服務，透過 Callback 來等待回傳成功或失敗的資料
        call.enqueue(
            object : Callback<List<JsonplaceholderPost>> {
                override fun onResponse(
                    call: Call<List<JsonplaceholderPost>>,
                    response: Response<List<JsonplaceholderPost>>
                ) {
                    response.body()?.forEach { item ->
                        Log.d(tag, "id: " + item.id)
                        Log.d(tag, "title: " + item.title)
                        Log.d(tag, "body: " + item.body)
                        Log.d(tag, "userId: " + item.userId)
                    }
                }

                override fun onFailure(
                    call: Call<List<JsonplaceholderPost>>,
                    t: Throwable
                ) {
                    Log.d(tag, "response: $t")
                }
            }
        )
    }
}
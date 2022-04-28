package com.rulhouse.retrofitpractice

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GetData {
    val tag = this::class.simpleName
    // baseUrl: 定義 API 網域網址，即是我們剛剛拆出來的前綴共用的部分
    // ddConverterFactory: 定義要將資料轉成什麼格式
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val fakeAPIService = retrofit.create(FakeAPIService::class.java)

    fun getSingle() {
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

    fun postSingle() {
        val post = JsonplaceholderPost(id = 21, userId = 21, title = "this is a title", body = "this is a body") //new一筆資料

        val call: Call<JsonplaceholderPost> = fakeAPIService.postsData(post) //發送到posts_data函數中，資料Post型態為@Body發送

        call.enqueue(object : Callback<JsonplaceholderPost> {
            //建立call連線
            override fun onResponse(call: Call<JsonplaceholderPost>, response: Response<JsonplaceholderPost>) { //連線成功，取得回傳的東西
                Log.d(tag, "response call:" + response.code())
                Log.d(tag, "Userid:" + response.body()?.userId)
                Log.d(tag, "Id:" + response.body()?.id)
                Log.d(tag, "Title:" + response.body()?.title)
                Log.d(tag, "Body:" + response.body()?.body)
            }

            override fun onFailure(call: Call<JsonplaceholderPost>, t: Throwable) { //連線失敗
                Log.d(tag, "fail")
            }
        })
    }
}
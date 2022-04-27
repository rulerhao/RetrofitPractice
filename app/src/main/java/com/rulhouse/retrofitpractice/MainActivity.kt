package com.rulhouse.retrofitpractice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rulhouse.retrofitpractice.ui.theme.RetrofitPracticeTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitPracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val tag = MainActivity::class.simpleName

    Text(text = "Hello $name!")
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
    call.enqueue(object : Callback<JsonplaceholderPost?> {
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
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RetrofitPracticeTheme {
        Greeting("Android")
    }
}
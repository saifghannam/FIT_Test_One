package com.example.fit_test_one

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fit_test_one.Adapter.RecaitalView
import com.example.fit_test_one.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    var listData:ArrayList<APIData> = ArrayList()

    // var recitalView:RecaitalView=RecaitalView()   Create Ojecte


    val recitalView:RecaitalView by lazy {
        RecaitalView()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.Resical_id)

        recyclerView.adapter = recitalView



        // API Call
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val servesAPI = retrofit.create(ServesAPI::class.java)

        val call: Call<APIData> = servesAPI.getPost()
        call.enqueue(object : Callback<APIData> {
            override fun onResponse(call: Call<APIData>, response: Response<APIData>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null ) {
                        listData.add(APIData(
                            responseBody.url,
                            responseBody.title,
                           responseBody.thumbnailUrl


                        ))
                        recitalView.setList(listData)
                    } else {
                        Log.d("myActvit", "Response body is empty.")
                    }
                } else {
                    Log.d("myActvit", "Response not successful.")
                }
            }

            override fun onFailure(call: Call<APIData>, t: Throwable) {
                Log.d("myActvit", "Response not successful.")
            }
        })
    }
}

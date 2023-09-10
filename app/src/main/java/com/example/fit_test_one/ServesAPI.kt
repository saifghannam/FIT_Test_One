package com.example.fit_test_one

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServesAPI {


    @GET("photos/1")
     fun getPost(): Call<APIData>
   // fun getPost(@Query("id") id: String): Call<List<APIData>>
//  @POST("posts")
//  fun setPost(@Body postData: SetData): Call<SetData>

}
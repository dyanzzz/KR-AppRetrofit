package com.kotlin.appretrofit

import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("/posts")
    fun getAllPosts(): Call<ArrayList<PostResponse>>

    @POST("/posts")
    @FormUrlEncoded
    fun createPost(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Call<CreatePostResponse>
}
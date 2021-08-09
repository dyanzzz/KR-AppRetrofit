package com.kotlin.appretrofit

import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("/posts")
    fun getAllPosts(): Call<ArrayList<PostResponse>>

    @GET("/posts")
    fun getAllPostsWithQueryParams(
        // query parameter
        @Query("userId") userId: Int,
        @Query("id") id: Int
    ): Call<ArrayList<PostResponse>>

    // manipulasi URL query params dengan hashmap
    @GET("/posts")
    fun getPosts(
        @QueryMap parameters: HashMap<String, String>
    ): Call<ArrayList<PostResponse>>

    // manipulasi dengan url langsung pada activity
    @GET
    fun getPostComments(@Url url: String): Call<ArrayList<PostResponse>>

    @GET("/posts/{id}/comments")
    fun getCommentByPostId(@Path("id") postId: Int): Call<ArrayList<CommentResponse>>

    @POST("/posts")
    @FormUrlEncoded
    fun createPost(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Call<CreatePostResponse>
}
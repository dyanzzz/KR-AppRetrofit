package com.kotlin.appretrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel: ViewModel() {

    // ini merupakan mutable live data yg merupakan arraylist dari data post
    val listPost = MutableLiveData<ArrayList<PostResponse>>()
    val listCreatePost = MutableLiveData<CreatePostResponse>()
    private var responseCode: Int = 0

    // function setter
    fun setPostData() {
        RetrofitClient.apiInstance
            .getAllPosts()
            .enqueue(object : Callback<ArrayList<PostResponse>>{
                override fun onResponse(
                    call: Call<ArrayList<PostResponse>>,
                    response: Response<ArrayList<PostResponse>>
                ) {
                    if (response.isSuccessful) {
                        listPost.postValue(response.body())
                        responseCode = response.code()
                    }
                    println("### Response : ${response.code()}")
                }

                override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {
                    println("##### Failure : ${t.message}")
                }
            })
    }

    fun createPostData() {
        RetrofitClient.apiInstance
            .createPost(
                29,
                "Retrofit tutorial",
                "retrofit body text"
            )
            .enqueue(object : Callback<CreatePostResponse>{
                override fun onResponse(
                    call: Call<CreatePostResponse>,
                    response: Response<CreatePostResponse>
                ) {
                    if (response.isSuccessful) {
                        listCreatePost.postValue(response.body())
                        responseCode = response.code()
                    }
                    println("### Response : ${response.body()}")

                }

                override fun onFailure(call: Call<CreatePostResponse>, t: Throwable) {
                    println("##### Failure : ${t.message}")
                }

            })
    }

    fun getResponseCode(): Int {
        println("##### ResponseCode $responseCode")
        return responseCode
    }

    fun getCreatePostData(): LiveData<CreatePostResponse>{
        println("### getCreatePostData $listCreatePost")
        return listCreatePost
    }

    fun getPostData(): LiveData<ArrayList<PostResponse>>{
        println("##### PostData $listPost")
        return listPost
    }
}
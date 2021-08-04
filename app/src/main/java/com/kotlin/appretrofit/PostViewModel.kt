package com.kotlin.appretrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.appretrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel: ViewModel() {

    // ini merupakan mutable live data yg merupakan arraylist dari data post
    val listPost = MutableLiveData<ArrayList<PostResponse>>()

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
                    }
                    println("Response : ${response.body()}")
                }

                override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {
                    println("Failure : ${t.message}")
                }
            })
    }

    fun getPostData(): LiveData<ArrayList<PostResponse>>{
        return listPost
    }
}
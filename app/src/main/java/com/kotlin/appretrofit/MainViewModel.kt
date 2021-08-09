package com.kotlin.appretrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    // ini merupakan mutable live data yg merupakan arraylist dari data post
    val listPost = MutableLiveData<ArrayList<PostResponse>>()
    val listCreatePost = MutableLiveData<CreatePostResponse>()
    val listComment = MutableLiveData<ArrayList<CommentResponse>>()
    private var responseCode: Int = 0

    // function setter
    fun setPostData() {

        val parameters = HashMap<String, String>()
        parameters["userId"] = "4"
        parameters["id"] = "32"

        RetrofitClient.apiInstance
            //.getAllPosts()                                // show all post
            .getAllPostsWithQueryParams(4, 32)     // show all post with query param in API
            //.getPosts(parameters)                     // show all post by query param with hashmap
            //.getPostComments("/posts?userId=4&id=32") // show all post by param URL in activity
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

    fun showComment() {
        RetrofitClient.apiInstance
            .getCommentByPostId(2)
            .enqueue(object : Callback<ArrayList<CommentResponse>>{
                override fun onResponse(
                    call: Call<ArrayList<CommentResponse>>,
                    response: Response<ArrayList<CommentResponse>>
                ) {
                    if (response.isSuccessful) {
                        listComment.postValue(response.body())
                        responseCode = response.code()
                    }
                    println("### Response : ${response.body()}")
                }

                override fun onFailure(call: Call<ArrayList<CommentResponse>>, t: Throwable) {
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

    fun getComment(): LiveData<ArrayList<CommentResponse>>{
        println("##### getComment $listComment")
        return listComment
    }
}
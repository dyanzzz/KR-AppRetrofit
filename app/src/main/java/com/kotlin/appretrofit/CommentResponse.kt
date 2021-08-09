package com.kotlin.appretrofit

data class CommentResponse(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String

)

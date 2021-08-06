package com.kotlin.appretrofit

import com.google.gson.annotations.SerializedName

data class CreatePostResponse(
    val id: Int,
    val userId: String?,
    val title: String?,
    @SerializedName("body")
    val text: String?
)

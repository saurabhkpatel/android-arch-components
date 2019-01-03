package com.saurabhpatel.apps.models.photo

import com.google.gson.annotations.SerializedName

data class Photo(

        @field:SerializedName("largeImageURL")
        val largeImageURL: String,

        @field:SerializedName("previewURL")
        val previewURL: String,

        @field:SerializedName("user_id")
        val userId: String,

        @field:SerializedName("id")
        val id: String,

        @field:SerializedName("type")
        val type: String?,

        @field:SerializedName("user")
        val user: String?,

        @field:SerializedName("likes")
        val likes: Int,

        @field:SerializedName("views")
        val views: String?
)

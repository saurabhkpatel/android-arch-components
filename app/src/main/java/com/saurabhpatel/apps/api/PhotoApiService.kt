package com.saurabhpatel.apps.api

import androidx.lifecycle.LiveData
import com.saurabhpatel.apps.models.photo.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApiService {

    companion object {
        val BASE_URL = "https://pixabay.com"
        const val API_SEGMENT = "api"
        const val PHOTO_QUERY_PARAM = "photo"
    }

    private val PIXABAY_KEY: String
        get() = "pixel3"

    @GET(API_SEGMENT)
    fun fetchPhotos(
            @Query("q") searchText: String,
            @Query("page") page: Int = 0,
            @Query("image_type") imageType: String = PHOTO_QUERY_PARAM,
            @Query("key") apiKey: String = PIXABAY_KEY): LiveData<ApiResponse<List<Photo>>>

}

package com.saurabhpatel.apps.api

import androidx.lifecycle.LiveData
import com.saurabhpatel.apps.photos.datamodels.PhotoSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApiService {

    companion object {
        val BASE_URL = "https://pixabay.com"
        const val API_SEGMENT = "api"
        const val PHOTO_QUERY_PARAM = "photo"
    }

    private val PIXABAY_KEY: String
        get() = "11095401-11227b412z74feece2dcc3ae8"

    @GET(API_SEGMENT)
    fun searchPhotos(
            @Query("q") searchText: String,
            @Query("page") page: Int = 1,
            @Query("image_type") imageType: String = PHOTO_QUERY_PARAM,
            @Query("key") apiKey: String = PIXABAY_KEY): LiveData<ApiResponse<PhotoSearchResponse>>


    @GET(API_SEGMENT)
    fun searchPhotosPagedList(
            @Query("q") searchText: String,
            @Query("page") page: Int = 1,
            @Query("image_type") imageType: String = PHOTO_QUERY_PARAM,
            @Query("key") apiKey: String = PIXABAY_KEY): Call<PhotoSearchResponse>


}

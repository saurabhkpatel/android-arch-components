package com.saurabhpatel.apps.repositories.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.saurabhpatel.apps.api.ApiResponse
import com.saurabhpatel.apps.api.PhotoApiService
import com.saurabhpatel.apps.photos.datamodels.Photo
import com.saurabhpatel.apps.photos.datamodels.PhotoSearchResponse
import com.saurabhpatel.apps.repositories.datamanager.AppExecutors
import com.saurabhpatel.apps.repositories.datamanager.NetworkBoundResource
import com.saurabhpatel.apps.repositories.datamanager.Resource
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class PhotosRepoImpl @Inject constructor(private val photosApiService: PhotoApiService,
                                         @param:Named("default") private val photosApiServiceDefault: PhotoApiService,
                                         private val appExecutors: AppExecutors) : PhotosRepo {

    override fun fetchPhotos(queryText: String, pageNumber: Int): LiveData<Resource<List<Photo>>> {

        return object : NetworkBoundResource<List<Photo>, PhotoSearchResponse>(appExecutors) {

            override fun getResultType(processResponse: PhotoSearchResponse): List<Photo> = processResponse.photos

            override fun shouldFetch(data: List<Photo>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Photo>> {
                val emptyPhotosList: List<Photo> = emptyList()
                val emptyPhotosListLiveData: MutableLiveData<List<Photo>> = MutableLiveData<List<Photo>>()
                emptyPhotosListLiveData.value = emptyPhotosList
                return emptyPhotosListLiveData
            }

            override fun createCall(): LiveData<ApiResponse<PhotoSearchResponse>> {
                return photosApiService.searchPhotos(queryText, pageNumber)
            }
        }.asLiveData()

    }

    override fun fetchPhotos(queryText: String, pageNumber: Int, onSuccessResponse: (List<Photo>) -> Unit, onFailureResponse: (String) -> Unit) {

        photosApiServiceDefault.searchPhotosPagedList(queryText, pageNumber).enqueue(object : retrofit2.Callback<PhotoSearchResponse> {

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<PhotoSearchResponse>, t: Throwable) {
                onFailureResponse("error message: ${t.message}")
            }

            /**
             * Invoked for a received HTTP response.
             * <p>
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call {@link Response#isSuccessful()} to determine if the response indicates success.
             */
            override fun onResponse(call: Call<PhotoSearchResponse>, response: Response<PhotoSearchResponse>) {
                if (response.isSuccessful) {
                    val photos = response.body()?.photos ?: emptyList()
                    onSuccessResponse(photos)
                } else {
                    onFailureResponse("error code: ${response.code()}")
                }
            }
        })
    }
}
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
import javax.inject.Inject

class PhotosRepoImpl @Inject constructor(private val photosApiService: PhotoApiService,
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
                return photosApiService.fetchPhotos(queryText, pageNumber)
            }
        }.asLiveData()

    }
}
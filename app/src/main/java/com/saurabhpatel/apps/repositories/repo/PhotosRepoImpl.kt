package com.saurabhpatel.apps.repositories.repo

import androidx.lifecycle.LiveData
import com.saurabhpatel.apps.api.ApiResponse
import com.saurabhpatel.apps.api.PhotoApiService
import com.saurabhpatel.apps.models.photo.Photo
import com.saurabhpatel.apps.repositories.datamanager.AppExecutors
import com.saurabhpatel.apps.repositories.datamanager.NetworkBoundResource
import com.saurabhpatel.apps.repositories.datamanager.Resource
import javax.inject.Inject

class PhotosRepoImpl @Inject constructor(private val photosApiService: PhotoApiService,
                                         private val appExecutors: AppExecutors) : PhotosRepo {

    override fun fetchPhotos(queryText: String, pageNumber: Int): LiveData<Resource<List<Photo>>> {

        return object : NetworkBoundResource<List<Photo>, List<Photo>>(appExecutors) {

            override fun saveCallResult(item: List<Photo>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun shouldFetch(data: List<Photo>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Photo>> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun createCall(): LiveData<ApiResponse<List<Photo>>> {
                return photosApiService.fetchPhotos(queryText, pageNumber)
            }
        }.asLiveData()

    }
}
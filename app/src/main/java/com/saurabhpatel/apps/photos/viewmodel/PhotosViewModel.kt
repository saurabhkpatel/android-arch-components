package com.saurabhpatel.apps.photos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.saurabhpatel.apps.models.photo.Photo
import com.saurabhpatel.apps.repositories.datamanager.Resource
import com.saurabhpatel.apps.repositories.repo.PhotosRepoImpl
import javax.inject.Inject

class PhotosViewModel @Inject constructor(private val photosRepoImpl: PhotosRepoImpl) : ViewModel() {

    private val searchQuery: MutableLiveData<String> = MutableLiveData()

    val photosResourceLiveData = Transformations.switchMap(searchQuery) { searchQuery ->
        photosRepoImpl.fetchPhotos(searchQuery, 0)
    }

    fun getPhotos(): LiveData<Resource<List<Photo>>> {
        return photosResourceLiveData;
    }

    fun setSearchQuery(searchQuery: String) {
        this.searchQuery.value = searchQuery
    }
}
package com.saurabhpatel.apps.photos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.saurabhpatel.apps.photos.datamodels.Photo
import com.saurabhpatel.apps.repositories.datamanager.Resource
import com.saurabhpatel.apps.repositories.datamanager.Status
import com.saurabhpatel.apps.repositories.repo.PhotosRepoImpl
import com.saurabhpatel.apps.repositories.repo.paging.PhotosPagingRepoImpl
import javax.inject.Inject

class PhotosViewModel @Inject constructor(private val photosRepoImpl: PhotosRepoImpl,
                                          private val photosPagingRepoImpl: PhotosPagingRepoImpl) : ViewModel() {

    private val firstPageNumber = 1
    private val searchQuery: MutableLiveData<String> = MutableLiveData()

    private val photosResourceLiveData = Transformations.switchMap(searchQuery) { searchQuery ->
        photosRepoImpl.fetchPhotos(searchQuery, firstPageNumber)
    }

    private val photosPagedListLiveData: LiveData<PagedList<Photo>> = Transformations.switchMap(searchQuery) { searchQuery ->
        photosPagingRepoImpl.fetchPhotos(searchQuery, firstPageNumber).pagedList
    }

    private val photosPagedListNetworkState: LiveData<Status> = Transformations.switchMap(searchQuery) { searchQuery ->
        photosPagingRepoImpl.fetchPhotos(searchQuery, firstPageNumber).networkState
    }

    fun getPhotosResourceLiveData(): LiveData<Resource<List<Photo>>> {
        return photosResourceLiveData;
    }

    fun getPhotosPagedListLiveData(): LiveData<PagedList<Photo>> {
        return photosPagedListLiveData;
    }

    fun getPhotosPagedListNetworkState(): LiveData<Status> {
        return photosPagedListNetworkState;
    }

    fun setSearchQuery(searchQuery: String) {
        this.searchQuery.value = searchQuery
    }
}
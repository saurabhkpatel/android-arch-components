package com.saurabhpatel.apps.repositories.repo.paging

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.saurabhpatel.apps.photos.datamodels.Photo
import com.saurabhpatel.apps.repositories.datamanager.Status
import com.saurabhpatel.apps.repositories.repo.PhotosRepoImpl

class PageKeyedPhotoDataSource(
        private val photosRepoImpl: PhotosRepoImpl,
        private val searchQuery: String) : PageKeyedDataSource<Int, Photo>() {

    val networkState = MutableLiveData<Status>()
    val initialLoad = MutableLiveData<Status>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Photo>) {
        val currentPage = 1
        val nextPage = currentPage + 1
        makeLoadInitialRequest(params, callback, null, currentPage, nextPage)
    }

    private fun makeLoadInitialRequest(params: LoadInitialParams<Int>,
                                       callback: LoadInitialCallback<Int, Photo>,
                                       previousPage: Int?,
                                       currentPage: Int,
                                       nextPage: Int?) {
        val fetchPhotos = photosRepoImpl.fetchPhotos(searchQuery, currentPage)

        val result = MediatorLiveData<List<Photo>>()
        result.addSource(fetchPhotos) { newChangedValue ->
            if (newChangedValue.status == Status.SUCCESS) {
                postInitialState(Status.SUCCESS)
                if (newChangedValue.data != null) {
                    callback.onResult(
                            newChangedValue.data,
                            previousPage,
                            nextPage
                    )
                }
            } else if (newChangedValue.status == Status.LOADING) {
                postInitialState(Status.LOADING)
            } else if (newChangedValue.status == Status.ERROR) {
                postInitialState(Status.ERROR)
            }
        }
    }


    private fun postInitialState(status: Status) {
        networkState.postValue(status)
        initialLoad.postValue(status)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        val currentPage = params.key
        val nextPage = currentPage + 1
        makeLoadAfterRequest(params, callback, currentPage, nextPage)
    }

    private fun makeLoadAfterRequest(params: LoadParams<Int>,
                                     callback: LoadCallback<Int, Photo>,
                                     currentPage: Int,
                                     nextPage: Int) {
        val fetchPhotos = photosRepoImpl.fetchPhotos(searchQuery, currentPage)

        val result = MediatorLiveData<List<Photo>>()
        result.addSource(fetchPhotos) { newChangedValue ->
            if (newChangedValue.status == Status.SUCCESS) {
                networkState.postValue(Status.SUCCESS)
                if (newChangedValue.data != null) {
                    callback.onResult(newChangedValue.data, nextPage)
                }
            } else if (newChangedValue.status == Status.LOADING) {
                networkState.postValue(Status.LOADING)
            } else if (newChangedValue.status == Status.ERROR) {
                networkState.postValue(Status.ERROR)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        val currentPage = params.key
        val previousPage = currentPage - 1
        makeLoadAfterRequest(params, callback, currentPage, previousPage)
    }

    private fun makeLoadBeforeRequest(params: LoadParams<Int>,
                                      callback: LoadCallback<Int, Photo>,
                                      currentPage: Int,
                                      previousPage: Int) {
        val fetchPhotos = photosRepoImpl.fetchPhotos(searchQuery, currentPage)

        val result = MediatorLiveData<List<Photo>>()
        result.addSource(fetchPhotos) { newChangedValue ->
            if (newChangedValue.status == Status.SUCCESS) {
                networkState.postValue(Status.SUCCESS)
                if (newChangedValue.data != null) {
                    callback.onResult(newChangedValue.data, previousPage)
                }
            } else if (newChangedValue.status == Status.LOADING) {
                networkState.postValue(Status.LOADING)
            } else if (newChangedValue.status == Status.ERROR) {
                networkState.postValue(Status.ERROR)
            }
        }
    }
}
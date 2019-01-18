package com.saurabhpatel.apps.repositories.repo.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.saurabhpatel.apps.photos.datamodels.Photo
import com.saurabhpatel.apps.repositories.datamanager.Status
import com.saurabhpatel.apps.repositories.repo.PhotosRepoImpl
import timber.log.Timber

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
        photosRepoImpl.fetchPhotos(
                searchQuery,
                currentPage,
                onSuccessResponse = fun(photos: List<Photo>) {
                    callback.onResult(photos, previousPage, nextPage)
                },
                onFailureResponse = fun(errorMessage: String) {
                    Timber.d(errorMessage)
                })
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
        photosRepoImpl.fetchPhotos(
                searchQuery,
                currentPage,
                onSuccessResponse = fun(photos: List<Photo>) {
                    callback.onResult(photos, nextPage)
                },
                onFailureResponse = fun(errorMessage: String) {
                    Timber.d(errorMessage)
                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        val currentPage = params.key
        val previousPage = currentPage - 1
        makeLoadBeforeRequest(params, callback, currentPage, previousPage)
    }

    private fun makeLoadBeforeRequest(params: LoadParams<Int>,
                                      callback: LoadCallback<Int, Photo>,
                                      currentPage: Int,
                                      previousPage: Int) {
        photosRepoImpl.fetchPhotos(
                searchQuery,
                currentPage,
                onSuccessResponse = fun(photos: List<Photo>) {
                    callback.onResult(photos, previousPage)
                },
                onFailureResponse = fun(errorMessage: String) {
                    Timber.d(errorMessage)
                })
    }
}
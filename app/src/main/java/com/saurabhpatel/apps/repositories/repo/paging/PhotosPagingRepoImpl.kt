package com.saurabhpatel.apps.repositories.repo.paging

import androidx.annotation.MainThread
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.saurabhpatel.apps.photos.datamodels.PagedListModel
import com.saurabhpatel.apps.photos.datamodels.Photo
import com.saurabhpatel.apps.repositories.datamanager.AppExecutors
import com.saurabhpatel.apps.repositories.repo.PhotosRepoImpl
import javax.inject.Inject

class PhotosPagingRepoImpl @Inject constructor(private val photosRepoImpl: PhotosRepoImpl,
                                               private val appExecutors: AppExecutors) {

    @MainThread
    public fun fetchPhotos(searchQuery: String, pageSize: Int): PagedListModel<Photo> {

        val factory = photoDataSourceFactory(searchQuery)

        val config = pagedListConfig(pageSize)

        val livePagedList = LivePagedListBuilder(factory, config).build()

        return PagedListModel(
                pagedList = livePagedList,
                networkState = Transformations.switchMap(factory.sourceLiveData) { it.networkState })
    }

    private fun photoDataSourceFactory(searchQuery: String): PhotoDataSourceFactory {
        return PhotoDataSourceFactory(photosRepoImpl, searchQuery)
    }

    private fun pagedListConfig(pageSize: Int): PagedList.Config {
        return PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(pageSize * 2)
                .setPageSize(pageSize)
                .build()
    }
}
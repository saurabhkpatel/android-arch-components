package com.saurabhpatel.apps.repositories.repo.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.saurabhpatel.apps.photos.datamodels.Photo
import com.saurabhpatel.apps.repositories.repo.PhotosRepoImpl

class PhotoDataSourceFactory(
        private val photosRepoImpl: PhotosRepoImpl,
        private val searchQuery: String) : DataSource.Factory<Int, Photo>() {

    val sourceLiveData = MutableLiveData<PageKeyedPhotoDataSource>()

    override fun create(): DataSource<Int, Photo> {
        val source = PageKeyedPhotoDataSource(photosRepoImpl, searchQuery)
        sourceLiveData.postValue(source)
        return source
    }
}

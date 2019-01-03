package com.saurabhpatel.apps.repositories.repo

import androidx.lifecycle.LiveData
import com.saurabhpatel.apps.models.photo.Photo
import com.saurabhpatel.apps.repositories.datamanager.Resource

internal interface PhotosRepo {
    fun fetchPhotos(queryText: String, pageNumber: Int): LiveData<Resource<List<Photo>>>
}

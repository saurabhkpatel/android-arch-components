package com.saurabhpatel.apps.photos.datamodels

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.saurabhpatel.apps.repositories.datamanager.Status

data class PagedListModel<T>(
        // the LiveData of paged lists for the UI to observe
        val pagedList: LiveData<PagedList<T>>,
        // represents the network request status to show to the user
        val networkState: LiveData<Status>
)

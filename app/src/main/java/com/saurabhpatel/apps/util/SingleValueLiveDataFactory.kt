package com.saurabhpatel.apps.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


object SingleValueLiveDataFactory {

    /**
     * LiveData that emits a single value
     */
    fun <T> singleValue(value: T): LiveData<T> {
        val liveData = MutableLiveData<T>()
        liveData.value = value
        return liveData
    }
}

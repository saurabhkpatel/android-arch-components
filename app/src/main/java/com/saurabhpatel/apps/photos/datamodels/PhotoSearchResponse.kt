package com.saurabhpatel.apps.photos.datamodels

import com.google.gson.annotations.SerializedName

data class PhotoSearchResponse(
        @SerializedName("totalHits")
        val total: Int = 0,
        @SerializedName("hits")
        val photos: List<Photo>
) {
    var nextPage: Int? = null
}

package com.saurabhpatel.apps.photos.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.saurabhpatel.apps.photos.datamodels.Photo

class PhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}
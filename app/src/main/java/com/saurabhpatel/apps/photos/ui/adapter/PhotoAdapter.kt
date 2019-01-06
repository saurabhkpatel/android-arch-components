package com.saurabhpatel.apps.photos.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saurabhpatel.apps.databinding.ListItemPhotoBinding
import com.saurabhpatel.apps.photos.datamodels.Photo


class PhotoAdapter : ListAdapter<Photo, PhotoAdapter.ListPhotoViewHolder>(PhotoDiffCallback()) {

    override fun onBindViewHolder(holder: ListPhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.apply {
            bindView(photo)
            itemView.tag = photo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItemPhotoBinding = ListItemPhotoBinding.inflate(layoutInflater, parent, false)
        return ListPhotoViewHolder(listItemPhotoBinding)

    }

    inner class ListPhotoViewHolder(private val listItemPhotoBinding: ListItemPhotoBinding) : RecyclerView.ViewHolder(listItemPhotoBinding.root) {
        fun bindView(photoItem: Photo) {
            listItemPhotoBinding.apply {
                photo = photoItem
                executePendingBindings()
            }
        }
    }
}
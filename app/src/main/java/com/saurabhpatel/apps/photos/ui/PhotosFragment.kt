package com.saurabhpatel.apps.photos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.saurabhpatel.apps.databinding.FragmentPhotosBinding
import com.saurabhpatel.apps.photos.viewmodel.PhotosViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PhotosFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var photosViewModel: PhotosViewModel
    private lateinit var fragmentPhotosBinding: FragmentPhotosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        photosViewModel = ViewModelProviders.of(this, viewModelFactory).get(PhotosViewModel::class.java)
        fragmentPhotosBinding = FragmentPhotosBinding.inflate(inflater, container, false)
        fragmentPhotosBinding.setLifecycleOwner(this)
        return fragmentPhotosBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
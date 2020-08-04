package com.coxassginment.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.coxassginment.presentation.util.imageUtil.Photon

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun loadImage(imageView: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            Photon.getInstance( imageView.context, 4*1024).displayImage(url,imageView)
            //Glide.with(imageView).load(url).into(imageView)
        }
    }
}
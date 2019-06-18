package com.mindtickle.android.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import javax.inject.Inject

open class ImageLoader @Inject constructor() {
    fun loadImage(imageView: ImageView, url: String) {
        val context = imageView.context
        Glide.with(context)
                .load(url)
                .into(imageView)
    }
}
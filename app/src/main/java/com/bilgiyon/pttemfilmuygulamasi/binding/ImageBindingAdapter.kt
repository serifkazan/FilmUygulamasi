package com.bilgiyon.pttemfilmuygulamasi.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bilgiyon.pttemfilmuygulamasi.util.Constants
import com.bumptech.glide.Glide

object ImageBindingAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        if (url.isNotEmpty()) {
            Glide.with(imageView.context)
                .load(Constants.IMAGE_BASE_URL + Constants.IMAGE_W342 + url)
                .into(imageView)
        }
    }
}
package com.example.paulsample.utils.extensions

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.paulsample.R

fun ImageView.load(context: Context, url: String) {

    Glide.with(context)
        .load(url)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_banner_image)
                .error(R.drawable.loading_banner_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        )
        .into(this)
}

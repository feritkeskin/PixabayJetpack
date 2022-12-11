package com.feritkeskin.pixabayjetpack.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(imageUrl: String) {
    Glide.with(this.context).load(imageUrl).into(this)
}
package com.oguzhanturkmen.pixselectcasestudy.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("android:downloadUrl")
fun ImageView.downloadImage(url: String?) {
    Glide.with(context).load(url).into(this)
}


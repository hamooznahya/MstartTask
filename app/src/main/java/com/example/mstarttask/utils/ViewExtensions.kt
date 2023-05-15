package com.example.mstarttask.utils

import android.view.View
import com.example.mstarttask.databinding.LoadingProgressbarBinding


fun View.visible() {
    visibility = View.VISIBLE
}
fun View.gone() {
    visibility = View.GONE
}

fun LoadingProgressbarBinding.show() {
    this.loadingContainer.visible()
}

fun LoadingProgressbarBinding.hide() {
    this.loadingContainer.gone()
}




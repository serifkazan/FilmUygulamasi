package com.bilgiyon.pttemfilmuygulamasi.util

import android.content.Context
import android.os.Build
import android.view.View
import androidx.annotation.ColorRes

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
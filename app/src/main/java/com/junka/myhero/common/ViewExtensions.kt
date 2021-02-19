package com.junka.myhero.common

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.getDrawableCompat(@DrawableRes idRes: Int) : Drawable?{
    return ContextCompat.getDrawable(this,idRes)
}
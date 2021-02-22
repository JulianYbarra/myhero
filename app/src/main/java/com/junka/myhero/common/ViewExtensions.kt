package com.junka.myhero.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.getDrawableCompat(@DrawableRes idRes: Int) : Drawable?{
    return ContextCompat.getDrawable(this,idRes)
}

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}

fun View.showOrHide(visibility : Boolean){
    if(visibility) show() else hide()
}

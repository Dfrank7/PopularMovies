package com.francis.popularmovies.utility

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

fun loadPicture(context: View, path: String, imageView: ImageView){
    val img_url = "https://image.tmdb.org/t/p/w500"
    val poster_url = img_url+path
    Glide.with(context).load(poster_url).into(imageView)
}

fun isConnectionAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
}

fun useSnackBar(view: View, message: String){
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}

fun useToast(view: Context, message: String){
    Toast.makeText(view, message, Toast.LENGTH_SHORT).show()
}

//fun showSnackBar()
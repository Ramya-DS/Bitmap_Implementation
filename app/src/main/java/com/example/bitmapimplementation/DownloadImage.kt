package com.example.bitmapimplementation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.AsyncTask
import android.widget.ImageView
import androidx.core.content.ContextCompat
import java.lang.ref.WeakReference
import java.net.URL

class DownloadImage(private val imageView: WeakReference<ImageView>) :
    AsyncTask<String, Unit, Bitmap>() {
    override fun doInBackground(vararg params: String?): Bitmap {
        val inputStream = URL(params[0]).openStream()
        return BitmapFactory.decodeStream(inputStream)
    }

    override fun onPostExecute(result: Bitmap?) {
        val drawable =
            ContextCompat.getDrawable(imageView.get()!!.context, R.drawable.ic_android_black_24dp)

        imageView.get()!!.setImageBitmap(result ?: (drawable as BitmapDrawable).bitmap)
    }
}
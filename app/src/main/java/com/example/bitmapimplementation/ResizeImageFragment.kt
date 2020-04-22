package com.example.bitmapimplementation


import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

/**
 * A simple [Fragment] subclass.
 */
class ResizeImageFragment : Fragment() {

    companion object {
        fun newInstance(isGlide: Boolean): ResizeImageFragment {
            val fragment = ResizeImageFragment()
            val bundle = Bundle().apply {
                this.putBoolean("isGlide", isGlide)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    var isGlide = false
    lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.resize_image_layout, container, false)

        val widthText: EditText = rootView.findViewById(R.id.width)
        val heightText: EditText = rootView.findViewById(R.id.width)
        val resizeButton: Button = rootView.findViewById(R.id.resize)
        imageView = rootView.findViewById(R.id.image)

        resizeButton.setOnClickListener {
            when {
                checkTextBoxes(widthText) -> Toast.makeText(
                    context,
                    "Enter width",
                    Toast.LENGTH_SHORT
                ).show()
                checkTextBoxes(heightText) -> Toast.makeText(
                    context,
                    "Enter height",
                    Toast.LENGTH_SHORT
                ).show()
                else -> resize(
                    widthText.text.toString().trim().toInt(),
                    heightText.text.toString().trim().toInt()
                )
            }
        }
        return rootView
    }

    private fun checkTextBoxes(editText: EditText): Boolean {
        return editText.text.toString().trim() == ""
    }

    private fun resize(width: Int, height: Int) {
        if (isGlide) {
            Glide.with(context!!)
                .load(R.drawable.ic_android_black_24dp)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions().override(width, height))
                .into(imageView)
        } else {

            imageView.setImageBitmap(
                convertDrawableToBitmap(
                    resources.getDrawable(R.drawable.ic_android_black_24dp),
                    width,
                    height
                )
            )

        }
    }

    private fun convertDrawableToBitmap(drawable: Drawable, width: Int, height: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(
            width,
            height,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isGlide = arguments!!.getBoolean("isGlide")
    }

}

package com.example.bitmapimplementation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.lang.ref.WeakReference

/**
 * A simple [Fragment] subclass.
 */
class UrlImageFragment : Fragment() {

    companion object {
        fun newInstance(isGlide: Boolean): UrlImageFragment {
            val fragment = UrlImageFragment()
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
        val rootView = inflater.inflate(R.layout.url_image_layout, container, false)

        val urlText: EditText = rootView.findViewById(R.id.urlEditText)
        val fetchButton: Button = rootView.findViewById(R.id.fetch)
        imageView = rootView.findViewById(R.id.url_image)

        fetchButton.setOnClickListener {
            if (checkTextBoxes(urlText))
                Toast.makeText(context!!, "Enter a url", Toast.LENGTH_SHORT).show()
            else
                getImage(urlText.text.toString().trim())
        }

        return rootView
    }

    private fun checkTextBoxes(editText: EditText): Boolean {
        return editText.text.toString().trim() == ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isGlide = arguments!!.getBoolean("isGlide")
    }

    private fun getImage(url: String) {
        if (isGlide) {
            Glide.with(context!!)
                .load(url)
                .placeholder(resources.getDrawable(R.drawable.ic_android_black_24dp))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        } else {
            DownloadImage(WeakReference(imageView)).execute(url)
        }
    }
}

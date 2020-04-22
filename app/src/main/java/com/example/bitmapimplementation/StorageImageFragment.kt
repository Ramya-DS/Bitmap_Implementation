package com.example.bitmapimplementation


import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

/**
 * A simple [Fragment] subclass.
 */
class StorageImageFragment : Fragment() {

    companion object {
        fun newInstance(isGlide: Boolean): StorageImageFragment {
            val fragment = StorageImageFragment()
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
        val rootView = inflater.inflate(R.layout.storage_image_layout, container, false)

        val pickButton: Button = rootView.findViewById(R.id.pick)
        imageView = rootView.findViewById(R.id.storage_image)

        pickButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent, "PICK ANY IMAGE"), 1)
        }

        return rootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            if (isGlide) {
                Glide.with(context!!)
                    .load(data?.data!!)
                    .into(imageView)
            } else {
                val inputStream = activity!!.contentResolver.openInputStream(data?.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                imageView.setImageBitmap(bitmap)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isGlide = arguments!!.getBoolean("isGlide")
    }
}

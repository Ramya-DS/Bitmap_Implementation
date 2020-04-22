package com.example.bitmapimplementation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * A simple [Fragment] subclass.
 */
class ImplementationFragment : BottomSheetDialogFragment() {

    var mOnOptionSelectedListener: OnOptionSelectedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.implementation_options_layout, container, false)

        val apiButton = rootView.findViewById<Button>(R.id.api)
        val glideButton = rootView.findViewById<Button>(R.id.glide)

        apiButton.setOnClickListener {
            mOnOptionSelectedListener?.onOptionSelected(false)
            this.dismiss()
        }

        glideButton.setOnClickListener {
            mOnOptionSelectedListener?.onOptionSelected(true)
            this.dismiss()
        }
        return rootView
    }


}

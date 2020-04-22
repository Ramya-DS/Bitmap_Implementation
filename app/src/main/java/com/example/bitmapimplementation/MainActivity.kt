package com.example.bitmapimplementation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), OnOptionSelectedListener {

    var action = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val buttonLayout = findViewById<LinearLayout>(R.id.buttons)
        val storageButton = buttonLayout.findViewById<Button>(R.id.storage)
        val urlButton = buttonLayout.findViewById<Button>(R.id.url)
        val resizeButton: Button = buttonLayout.findViewById(R.id.resize)

        storageButton.setOnClickListener {
            action = 3
            showBottomSheet()
        }

        urlButton.setOnClickListener {
            action = 2
            showBottomSheet()
        }

        resizeButton.setOnClickListener {
            action = 1
            showBottomSheet()
        }
    }

    private fun showBottomSheet() {
        ImplementationFragment().show(supportFragmentManager, "BOTTOM SHEET")
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is ImplementationFragment)
            fragment.mOnOptionSelectedListener = this
    }

    override fun onOptionSelected(isGlide: Boolean) {
        val intent = Intent(applicationContext, ActionActivity::class.java)
        intent.putExtra("isGlide", isGlide)
        intent.putExtra("action", action)
        startActivity(intent)
    }
}

package com.example.bitmapimplementation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

class ActionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val action = intent.getIntExtra("action", 0)
        val isGlide = intent.getBooleanExtra("isGlide", false)

        insertActionFragment(action, isGlide)

    }


    private fun insertActionFragment(action: Int, isGlide: Boolean) {
        val fragment: Fragment = when (action) {
            1 -> {
                ResizeImageFragment.newInstance(isGlide)
            }
            2 -> {
                UrlImageFragment.newInstance(isGlide)
            }
            else -> {
                StorageImageFragment.newInstance(isGlide)
            }
        }

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}

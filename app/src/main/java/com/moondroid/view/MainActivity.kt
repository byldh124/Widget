package com.moondroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            throw RuntimeException("Hello")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
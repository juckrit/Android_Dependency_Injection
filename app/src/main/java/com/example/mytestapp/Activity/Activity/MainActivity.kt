package com.example.mytestapp.Activity.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestapp.Activity.Fragment.MainFragment
import com.example.mytestapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commit()
    }
}

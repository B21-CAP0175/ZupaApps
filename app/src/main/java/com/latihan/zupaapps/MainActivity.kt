package com.latihan.zupaapps

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var profilePic: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profilePic = findViewById(R.id.profile_pic)
        profilePic.setOnClickListener(this)
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.profile_pic->{
                startActivity(Intent(this, ProfileActivity::class.java))
            }
        }
    }

}
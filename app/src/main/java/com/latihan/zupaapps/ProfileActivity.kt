package com.latihan.zupaapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class ProfileActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var editProfile: Button
    private lateinit var backButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        editProfile = findViewById(R.id.btn_editProfile)
        backButton = findViewById(R.id.back_btn_profile)
        editProfile.setOnClickListener(this)
        backButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_editProfile->{
                startActivity(Intent(this, EditProfileActivity::class.java))
            }
            R.id.back_btn_profile->{
                finish()
            }
        }
    }
}
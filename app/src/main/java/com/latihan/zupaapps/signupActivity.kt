package com.latihan.zupaapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class signupActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mBackBtn :ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mBackBtn = findViewById(R.id.back_button_signup)
        mBackBtn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.back_button_signup->{
                finish()
            }
        }
    }

}
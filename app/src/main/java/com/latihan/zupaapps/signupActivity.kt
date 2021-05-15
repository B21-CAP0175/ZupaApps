package com.latihan.zupaapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class signupActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.back_button_signup->{
                startActivity(Intent(this@signupActivity, loginActivity::class.java))
            }
        }
    }
}
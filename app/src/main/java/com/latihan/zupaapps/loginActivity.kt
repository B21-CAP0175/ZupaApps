package com.latihan.zupaapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class loginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.button_signup->{
                startActivity(Intent(this@loginActivity, signupActivity::class.java))
            }
        }
    }
}
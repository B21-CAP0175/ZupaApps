package com.latihan.zupaapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class loginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signup = findViewById<Button>(R.id.button_signup)
        val login = findViewById<Button>(R.id.button_login)
        signup.setOnClickListener(){
            startActivity(Intent(this, signupActivity::class.java))
        }
        login.setOnClickListener(){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onClick(v: View) {

    }
}
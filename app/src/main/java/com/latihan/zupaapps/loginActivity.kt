package com.latihan.zupaapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class loginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var authEmail:TextInputEditText
    private lateinit var authPassword: TextInputEditText

    private lateinit var etlEmail: TextInputLayout
    private lateinit var etlPass: TextInputLayout

    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signup = findViewById<Button>(R.id.button_signup)
        signup.setOnClickListener(){
            startActivity(Intent(this, signupActivity::class.java))
        }

        loginBtn = findViewById(R.id.button_login)
        loginBtn.setOnClickListener(this)
    }

    private fun AuthEmail(): Boolean{
        authEmail = findViewById(R.id.text_input_login_email)
        etlEmail= findViewById(R.id.login_email)

        val emailPattern:String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val currentEmail:String = authEmail.text.toString().trim()

        if (currentEmail.isEmpty()){
            etlEmail.setError("Email tidak boleh kosong")
            return false
        }
        else if(!currentEmail.matches(emailPattern.toRegex())){
            etlEmail.setError("Email tidak valid")
            return false
        }
        else {
            etlEmail.setError(null)
            return true
        }
        return true
    }

    private fun AuthPassword(): Boolean{
        authPassword = findViewById(R.id.text_input_login_pass)
        etlPass = findViewById(R.id.login_password)

        val currentPass:String = authPassword.text.toString()

        if (currentPass.isEmpty()){
            etlPass.setError("Password tidak boleh kosong")
            return false
        }
        else {
            etlPass.setError(null)
            return true
        }
        return true
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.button_login->{
                if (!AuthEmail() or !AuthPassword()){
                    return
                }
                else if(authEmail.text.toString()=="admin@admin.com" && authPassword.text.toString()=="admin") {
                    etlPass.setError(null)
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        }
    }
}
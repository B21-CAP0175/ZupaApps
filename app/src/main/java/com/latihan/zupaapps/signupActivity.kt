package com.latihan.zupaapps

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class signupActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mBackBtn :ImageView
    private lateinit var mSignupBtn : Button

    private lateinit var regNama: TextInputEditText
    private lateinit var regNIK: TextInputEditText
    private lateinit var regNoKK: TextInputEditText
    private lateinit var regNoHP: TextInputEditText
    private lateinit var regEmail: TextInputEditText
    private lateinit var regPass: TextInputEditText
    private lateinit var regRePass: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mBackBtn = findViewById(R.id.back_button_signup)
        mBackBtn.setOnClickListener(this)

        mSignupBtn = findViewById(R.id.button_signup)
        mSignupBtn.setOnClickListener(this)
    }

    private fun validateNama(): Boolean{
        regNama = findViewById(R.id.text_input_signup_nama)

        val currentNama:String = regNama.text.toString()

        if (currentNama.isEmpty()){
            regNama.setError("Nama tidak boleh kosong")
            return false
        }
        else {
            regNama.setError(null)
            return true
        }
        return true
    }

    private fun validateNIK(): Boolean {
        regNIK = findViewById(R.id.text_input_signup_nik)

        val currentNik:String = regNIK.text.toString()
        val lengthNik:Int = 16

        if (currentNik.isEmpty()){
            regNIK.setError("NIK tidak boleh kosong")
            return false
        }
        else if(currentNik.length != lengthNik){
            regNIK.setError("Panjang NIK harus 16 angka")
            return false
        }
        else {
            regNIK.setError(null)
            return true
        }
        return true
    }

    private fun validateNoKK(): Boolean {
        regNoKK = findViewById(R.id.text_input_signup_nkk)

        val currentNoKK:String = regNoKK.text.toString()
        val lengthNoKK:Int = 16

        if (currentNoKK.isEmpty()){
            regNoKK.setError("No.KK tidak boleh kosong")
            return false
        }
        else if(currentNoKK.length != lengthNoKK){
            regNoKK.setError("Panjang No.KK harus 16 angka")
            return false
        }
        else {
            regNoKK.setError(null)
            return true
        }
        return true
    }

    private fun validateNoHP(): Boolean{
        regNoHP = findViewById(R.id.text_input_signup_nohp)

        val currentNoHp:String = regNoHP.text.toString()
        val lengthNoHp:Int = 12

        if (currentNoHp.isEmpty()){
            regNoHP.setError("No.Hp tidak boleh kosong")
            return false
        }
        else if(currentNoHp.length <= lengthNoHp){
            regNoHP.setError("Panjang maksimal No.Hp adalah 12 angka")
            return false
        }
        else {
            regNoHP.setError(null)
            return true
        }
        return true
    }

    private fun validateEmail(): Boolean{
        regEmail = findViewById(R.id.text_input_signup_email)
        val emailPattern:String = "[a-zA-Z0-9._-]\\+@[a-z]+\\.+[a-z]+"
        val currentEmail:String = regEmail.text.toString()

        if (currentEmail.isEmpty()){
            regEmail.setError("Email tidak boleh kosong")
            return false
        }
        else if(!currentEmail.contains(emailPattern)){
            regEmail.setError("Email tidak valid")
            return false
        }
        else {
            regEmail.setError(null)
            return true
        }
        return true
    }

    private fun validatePass(): Boolean{
        regPass = findViewById(R.id.text_input_signup_pass)
        val currentPass:String = regPass.text.toString()

        if (currentPass.isEmpty()){
            regPass.setError("Pssword tidak boleh kosong")
            return false
        }
        else {
            regPass.setError(null)
            return true
        }
        return true
    }

    private fun validateRePass(): Boolean{
        regRePass = findViewById(R.id.text_input_signup_repass)
        regPass = findViewById(R.id.text_input_signup_pass)

        val currentRePass:String = regRePass.text.toString()
        val currentPass:String = regPass.text.toString()

        if (currentRePass.isEmpty()){
            regRePass.setError("Password tidak boleh kosong")
            return false
        }
        else if(currentRePass != currentPass){
            regRePass.setError("Password tidak sama")
            return false
        }
        else {
            regRePass.setError(null)
            return true
        }
        return true
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.back_button_signup -> {
                finish()
            }
            R.id.button_signup -> {
                if (!validateNama() or !validateNIK() or !validateNoKK() or !validateNoHP() or !validateEmail() or !validatePass() or !validateRePass()) {
                    return
                }
                else{
                    finish()
                }
            }
        }
    }
}

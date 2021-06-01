package com.latihan.zupaapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class EditProfileActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var backBtn: ImageView
    private lateinit var saveBtn:Button

    private lateinit var etlEmail: TextInputLayout
    private lateinit var regEmail: TextInputEditText

    private lateinit var etlPass: TextInputLayout
    private lateinit var regPass: TextInputEditText

    private lateinit var etlRePass: TextInputLayout
    private lateinit var regRePass: TextInputEditText

    private lateinit var etlNoHp: TextInputLayout
    private lateinit var regNoHP: TextInputEditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        backBtn = findViewById(R.id.back_button_editprofile)
        backBtn.setOnClickListener(this)

        saveBtn = findViewById(R.id.button_save)
        saveBtn.setOnClickListener(this)
    }

    private fun validateEmail(): Boolean{
        etlEmail = findViewById(R.id.editEmail)
        regEmail = findViewById(R.id.text_input_editEmail)
        val emailPattern:String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val currentEmail:String = regEmail.text.toString().trim()

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

    private fun validateNoHP(): Boolean{
        etlNoHp = findViewById(R.id.editNomorHP)
        regNoHP = findViewById(R.id.text_input_editNoHP)

        val currentNoHp:String = regNoHP.text.toString()
        val lengthNoHp:Int = 13

        if (currentNoHp.isEmpty()){
            etlNoHp.setError("No.Hp tidak boleh kosong")
            return false
        }
        else if(currentNoHp.length > lengthNoHp){
            etlNoHp.setError("Panjang maksimal No.Hp adalah 13 angka")
            return false
        }
        else {
            etlNoHp.setError(null)
            return true
        }
        return true
    }

    private fun validatePass(): Boolean{
        etlPass = findViewById(R.id.editPass)
        regPass = findViewById(R.id.text_input_editPass)
        val passPatern:String = ".{4,}"
        val currentPass:String = regPass.text.toString()

        if (currentPass.isEmpty()){
            etlPass.setError("Pssword tidak boleh kosong")
            return false
        }
        else if (!currentPass.matches(passPatern.toRegex())){
            etlPass.setError("Password minimal 4 karakter")
            return false
        }
        else {
            etlPass.setError(null)
            return true
        }
        return true
    }

    private fun validateRePass(): Boolean{
        etlRePass = findViewById(R.id.editRepass)
        regRePass = findViewById(R.id.text_input_editRepass)
        regPass = findViewById(R.id.text_input_editPass)

        val currentRePass:String = regRePass.text.toString()
        val currentPass:String = regPass.text.toString()

        if (currentRePass.isEmpty()){
            etlRePass.setError("Password tidak boleh kosong")
            return false
        }
        else if(currentRePass != currentPass){
            etlRePass.setError("Password tidak sama")
            return false
        }
        else {
            etlRePass.setError(null)
            return true
        }
        return true
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.back_button_editprofile->{
                finish()
            }
            R.id.button_save->{
                if (!validateEmail() or !validatePass() or !validateNoHP() or !validateRePass()){
                    return
                }
                else {
                    Toast.makeText(this, "berhasil disimpan", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }
    }
}
package com.latihan.Capstone

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.latihan.Capstone.Database.UserDataBase.DataUserEntity
import com.latihan.Capstone.Database.UserDataBase.UserDataHelper

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

    private lateinit var etlNama: TextInputLayout
    private lateinit var etlNIK: TextInputLayout
    private lateinit var etlNoKK: TextInputLayout
    private lateinit var etlNoHp: TextInputLayout
    private lateinit var etlEmail: TextInputLayout
    private lateinit var etlPass: TextInputLayout
    private lateinit var etlRePass: TextInputLayout

    //DataBase
    private lateinit var getHelper: UserDataHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mBackBtn = findViewById(R.id.back_button_signup)
        mBackBtn.setOnClickListener(this)

        mSignupBtn = findViewById(R.id.button_signup)
        mSignupBtn.setOnClickListener(this)

        //Database
        getHelper = UserDataHelper.getInstance(applicationContext)
        getHelper.open()
    }

    private fun validateNama(): Boolean{
        etlNama = findViewById(R.id.signup_name)
        regNama = findViewById(R.id.text_input_signup_nama)

        val currentNama:String = regNama.text.toString()

        if (currentNama.isEmpty()){
            etlNama.setError("Nama tidak boleh kosong")
            return false
        }
        else {
            etlNama.setError(null)
            return true
        }
    }

    private fun validateNIK(): Boolean {
        etlNIK = findViewById(R.id.signup_nik)
        regNIK = findViewById(R.id.text_input_signup_nik)

        val currentNik:String = regNIK.text.toString()
        val lengthNik:Int = 16

        if (currentNik.isEmpty()){
            etlNIK.setError("NIK tidak boleh kosong")
            return false
        }
        else if(currentNik.length != lengthNik){
            etlNIK.setError("Panjang NIK harus 16 angka")
            return false
        }
        else {
            etlNIK.setError(null)
            return true
        }
    }

    private fun validateNoKK(): Boolean {
        etlNoKK = findViewById(R.id.signup_nkk)
        regNoKK = findViewById(R.id.text_input_signup_nkk)

        val currentNoKK:String = regNoKK.text.toString()
        val lengthNoKK:Int = 16

        if (currentNoKK.isEmpty()){
            etlNoKK.setError("No.KK tidak boleh kosong")
            return false
        }
        else if(currentNoKK.length != lengthNoKK){
            etlNoKK.setError("Panjang No.KK harus 16 angka")
            return false
        }
        else {
            etlNoKK.setError(null)
            return true
        }
    }

    private fun validateNoHP(): Boolean{
        etlNoHp = findViewById(R.id.signup_nohp)
        regNoHP = findViewById(R.id.text_input_signup_nohp)

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
    }

    private fun validateEmail(): Boolean{
        etlEmail = findViewById(R.id.signup_email)
        regEmail = findViewById(R.id.text_input_signup_email)
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
    }

    private fun validatePass(): Boolean{
        etlPass = findViewById(R.id.signup_pass)
        regPass = findViewById(R.id.text_input_signup_pass)
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
    }

    private fun validateRePass(): Boolean{
        etlRePass = findViewById(R.id.signup_repass)
        regRePass = findViewById(R.id.text_input_signup_repass)
        regPass = findViewById(R.id.text_input_signup_pass)

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
                    val dataUsername = findViewById<TextInputEditText>(R.id.text_input_signup_email).text.toString().trim()
                    val dataPassword =  findViewById<TextInputEditText>(R.id.text_input_signup_pass).text.toString().trim()
                    val dataLocation = "Click here to update your location"
                    val dataName = findViewById<TextInputEditText>(R.id.text_input_signup_nama).text.toString().trim()
                    val dataNIK = findViewById<TextInputEditText>(R.id.text_input_signup_nik).text.toString().trim()
                    val dataNKK = findViewById<TextInputEditText>(R.id.text_input_signup_nkk).text.toString().trim()
                    val dataNoHP = findViewById<TextInputEditText>(R.id.text_input_signup_nohp).text.toString().trim()

                    val values = ContentValues()
                    values.put(DataUserEntity.UserColumns.USERNAME, dataUsername)
                    values.put(DataUserEntity.UserColumns.PASSWORD, dataPassword)
                    values.put(DataUserEntity.UserColumns.LOCATION, dataLocation)
                    values.put(DataUserEntity.UserColumns.NAME, dataName)
                    values.put(DataUserEntity.UserColumns.NIK, dataNIK)
                    values.put(DataUserEntity.UserColumns.NKK, dataNKK)
                    values.put(DataUserEntity.UserColumns.NOHP, dataNoHP)
                    values.put(DataUserEntity.UserColumns.COUNTER, "0")
                    getHelper.insert(values)

                    Toast.makeText(this, getString(R.string.addUserSuccess), Toast.LENGTH_SHORT).show()
                    getHelper.close()
                    finish()
                }
            }
        }
    }
}

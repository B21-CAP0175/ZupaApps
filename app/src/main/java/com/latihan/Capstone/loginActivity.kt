package com.latihan.Capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jlibrosa.audio.wavFile.WavFile
import com.jlibrosa.audio.wavFile.WavFileException
import com.latihan.Capstone.Database.MappingHelper
import com.latihan.Capstone.Database.UserDataBase.UserDataHelper
import com.latihan.Capstone.Preprocess.MFCC
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.common.TensorProcessor
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.label.TensorLabel
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.io.IOException
import java.math.RoundingMode
import java.nio.ByteBuffer
import java.nio.MappedByteBuffer
import java.text.DecimalFormat
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class loginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var authEmail:TextInputEditText
    private lateinit var authPassword: TextInputEditText

    private lateinit var etlEmail: TextInputLayout
    private lateinit var etlPass: TextInputLayout

    private lateinit var loginBtn: Button


    //DataBase
    private lateinit var getHelper: UserDataHelper

    companion object{
        private val TAG = loginActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signup = findViewById<Button>(R.id.button_signup)
        signup.setOnClickListener(){
            startActivity(Intent(this, signupActivity::class.java))
        }

        loginBtn = findViewById(R.id.button_login)
        loginBtn.setOnClickListener(this)

        //Database
        getHelper = UserDataHelper.getInstance(applicationContext)
        getHelper.open()

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
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.button_login->{
                val UserCheck = findViewById<TextInputEditText>(R.id.text_input_login_email).text.toString()
                val PassCheck = findViewById<TextInputEditText>(R.id.text_input_login_pass).text.toString()
                val cursor = getHelper.queryById(UserCheck)

                if (!AuthEmail() or !AuthPassword()){
                    return
                }

                else if(authEmail.text.toString()=="admin@admin.com" && authPassword.text.toString()=="admin") {
                    etlPass.setError(null)
                    startActivity(Intent(this, MainActivity::class.java))
                }

                else if (cursor.count > 0) {
                    val users = MappingHelper.mapCursorToObject(cursor)
                    val usernames = users.username.toString()
                    val passwords = users.pass.toString()

                    if(UserCheck == usernames && PassCheck == passwords){
                        etlPass.setError(null)
                        val moveWithObjectIntent = Intent(this@loginActivity, MainActivity::class.java)
                        moveWithObjectIntent.putExtra(MainActivity.EXTRA_USER, users)
                        startActivity(moveWithObjectIntent)
                    }
                }
                else{
                    Toast.makeText(this, "Username dan Password is invalid", Toast.LENGTH_SHORT).show()
                    return
                }
            }
        }
    }
}
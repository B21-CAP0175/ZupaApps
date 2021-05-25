package com.latihan.zupaapps

import android.content.Intent
import android.media.MediaRecorder
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.text.Normalizer
import kotlin.math.log

class MainActivity : AppCompatActivity(), View.OnClickListener, OnTouchListener {
    private lateinit var profilePic: ImageView
    private lateinit var btnSOS: Button
    private lateinit var recordNotif: ImageView
    private lateinit var formButton: Button
    private lateinit var historyButton: Button
    private lateinit var logoutButton: Button

/*    private var AUDIO_RECORDER_FILE_EXT_OGG:String=".ogg"
    private var AUDIO_RECORDER_FOLDER:String="ZupaAudioRecorder"

    private var recorder: MediaRecorder? = null
    private var currentFormat: Int = 0
    private var output_formats: Int = MediaRecorder.OutputFormat.OGG
    private var file_exts: String = AUDIO_RECORDER_FILE_EXT_OGG*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profilePic = findViewById(R.id.profile_pic)
        profilePic.setOnClickListener(this)

        recordNotif = findViewById(R.id.nav_bicara)
        recordNotif.visibility = View.GONE

        btnSOS = findViewById(R.id.btnSOS)
        btnSOS.setOnTouchListener(this)

        formButton = findViewById(R.id.btn_form)
        formButton.setOnClickListener(this)

        historyButton = findViewById(R.id.btn_history)
        historyButton.setOnClickListener(this)

        logoutButton = findViewById(R.id.btn_log_out)
        logoutButton.setOnClickListener(this)
    }


    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
                recordNotif.startAnimation(anim)
                recordNotif.visibility = View.VISIBLE
//                startRecord()
                return true
            }
            MotionEvent.ACTION_UP -> {
                val anim = AnimationUtils.loadAnimation(this, R.anim.fade_out)
                recordNotif.startAnimation(anim)
                recordNotif.visibility = View.GONE
//                stopRecord()
            }
        }
        return false
    }

    private fun stopRecord() {
    }

    private fun getFilename() {
    }

    private fun startRecord() {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.profile_pic->{
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            R.id.btn_form->{
                startActivity(Intent(this, formActivity::class.java))
            }
            R.id.btn_history->{
                startActivity(Intent(this, HistoryActivity::class.java))
            }
            R.id.btn_log_out->{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Log Out")
                builder.setMessage("Anda yakin ingin keluar?")
                builder.setPositiveButton("Yes"){dialogInterface, which ->
                    finish()
                }
                builder.setNegativeButton("No"){dialogInterface, which ->
                    return@setNegativeButton
                }
                val alertDialog:AlertDialog = builder.create()
                alertDialog.show()
            }
        }
    }
}

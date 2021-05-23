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
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener, OnTouchListener {
    private lateinit var profilePic: ImageView
    private lateinit var btnSOS: Button
    private lateinit var recordNotif: ImageView

    private var AUDIO_RECORDER_FILE_EXT_OGG:String=".ogg"
    private var AUDIO_RECORDER_FOLDER:String="ZupaAudioRecorder"

    private var recorder: MediaRecorder? = null
    private var currentFormat: Int = 0
    private var output_formats: Int = MediaRecorder.OutputFormat.OGG
    private var file_exts: String = AUDIO_RECORDER_FILE_EXT_OGG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profilePic = findViewById(R.id.profile_pic)
        profilePic.setOnClickListener(this)

        recordNotif = findViewById(R.id.nav_bicara)
        recordNotif.visibility = View.GONE

        btnSOS = findViewById(R.id.btnSOS)
        btnSOS.setOnTouchListener(this)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }


    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
                recordNotif.startAnimation(anim)
                recordNotif.visibility = View.VISIBLE
                startRecord()
                return true
            }
            MotionEvent.ACTION_UP -> {
                val anim = AnimationUtils.loadAnimation(this, R.anim.fade_out)
                recordNotif.startAnimation(anim)
                recordNotif.visibility = View.GONE
                stopRecord()
            }
        }
        return false
    }

    private fun stopRecord() {
        recorder = MediaRecorder()
        recorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder!!.setOutputFormat(output_formats.get(currentFormat))
        recorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        recorder.setOutputFile(getFilename())
        recorder!!.setOnErrorListener(errorListener)
        recorder!!.setOnInfoListener(infoListener)

        try {
            recorder!!.prepare()
            recorder!!.start()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
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
        }
    }
}

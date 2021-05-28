package com.latihan.zupaapps

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import java.util.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity(), View.OnClickListener, OnTouchListener {
    private lateinit var profilePic: ImageView
    private lateinit var btnSOS: Button
    private lateinit var recordNotif: ImageView
    private lateinit var formButton: Button
    private lateinit var historyButton: Button
    private lateinit var logoutButton: Button

    private lateinit var textLocation: TextView

    private var locationManager: LocationManager? = null

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

        textLocation = findViewById(R.id.textView_location)
        textLocation.setOnClickListener(this)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

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

    private fun locationListener() = object :LocationListener{
        override fun onLocationChanged(location: Location) {
            var gc = Geocoder(this@MainActivity,Locale.getDefault())
            var addresses = gc.getFromLocation(location.latitude,location.longitude,1)
            var address = addresses.get(0).getAddressLine(0)
            textLocation.text = (""+address)
            Toast.makeText(this@MainActivity, "Location Updated", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onClick(v: View){
        when(v.id){
            R.id.profile_pic->{
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            R.id.textView_location->{
                try {
                 locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0L,0f,locationListener())
                } catch (ex: SecurityException){
                    Log.d("myTag","Security Exception, no location available")
                }
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

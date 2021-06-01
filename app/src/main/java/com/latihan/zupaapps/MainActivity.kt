package com.latihan.zupaapps

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
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
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var profilePic: ImageView
    private lateinit var btnSOS: Button
    private lateinit var recordNotif: CardView
    private lateinit var formButton: Button
    private lateinit var historyButton: Button
    private lateinit var logoutButton: Button

    private lateinit var textLocation: TextView

    private var locationManager: LocationManager? = null

    private lateinit var textRecord: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profilePic = findViewById(R.id.profile_pic)
        profilePic.setOnClickListener(this)

        recordNotif = findViewById(R.id.cardView_sos)
        recordNotif.visibility = View.GONE

        btnSOS = findViewById(R.id.btnSOS)

        formButton = findViewById(R.id.btn_form)
        formButton.setOnClickListener(this)

        historyButton = findViewById(R.id.btn_history)
        historyButton.setOnClickListener(this)

        logoutButton = findViewById(R.id.btn_log_out)
        logoutButton.setOnClickListener(this)

        textLocation = findViewById(R.id.textView_location)
        textLocation.setOnClickListener(this)

        textRecord = findViewById(R.id.hasil_record)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION),111)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + packageName))
                startActivity(intent)
                finish()
                Toast.makeText(this, "Enable Microphone Permission..!!", Toast.LENGTH_SHORT).show()
            }
        }

        speechToText()

    }

    private fun speechToText(){
        val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault())

        speechRecognizer.setRecognitionListener(object : RecognitionListener{
            override fun onReadyForSpeech(params: Bundle?) {}

            override fun onBeginningOfSpeech() {}

            override fun onRmsChanged(rmsdB: Float) {}

            override fun onBufferReceived(buffer: ByteArray?) {}

            override fun onEndOfSpeech() {}

            override fun onError(error: Int) {}

            override fun onResults(results: Bundle) {
                val matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (matches != null){
                    textRecord.setText(matches[0])
                }
            }

            override fun onPartialResults(partialResults: Bundle?) {}

            override fun onEvent(eventType: Int, params: Bundle?) {}
        })

        btnSOS.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    val anim = AnimationUtils.loadAnimation(this, R.anim.fade_out)
                    recordNotif.startAnimation(anim)
                    recordNotif.visibility = View.GONE
                    speechRecognizer.stopListening()
                    textRecord.hint = "Hasil rekaman akan muncul disini"
                }

                MotionEvent.ACTION_DOWN -> {
                    val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
                    recordNotif.startAnimation(anim)
                    recordNotif.visibility = View.VISIBLE
                    speechRecognizer.startListening(speechRecognizerIntent)
                    textRecord.setText("")
                    textRecord.hint = "Listening..."
                }
            }
            false
        })
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

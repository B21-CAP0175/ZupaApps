package com.latihan.Capstone

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.media.AudioFormat
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.MotionEvent
import android.view.View
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
import com.jlibrosa.audio.JLibrosa
import com.latihan.Capstone.Database.DataClass.User
import com.latihan.Capstone.Database.FormDataBase.FormDataHelper
import com.latihan.Capstone.Database.MappingHelper
import com.latihan.Capstone.Database.UserDataBase.DataUserEntity
import com.latihan.Capstone.Database.UserDataBase.UserDataHelper
import com.latihan.Capstone.Database.UserDataBase.UserDatabaseHelper
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var profilePic: ImageView
    private lateinit var btnSOS: Button
    private lateinit var recordNotif: CardView
    private lateinit var formButton: Button
    private lateinit var historyButton: Button
    private lateinit var logoutButton: Button

    private lateinit var textLocation: TextView
    private lateinit var hasil: TextView

    private var locationManager: LocationManager? = null

    private lateinit var textRecord: TextView
    private lateinit var textBantuan: TextView

    private lateinit var user : User
    private lateinit var users : User
    private val TAG = "MainActivity"

    private var output: String? = null
    private var mediaRecorder: MediaRecorder? = null
    private var state: Boolean = false

    private var count : Int = 0

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    //DataBase
    private lateinit var getHelper: UserDataHelper
    private lateinit var getHelper2: FormDataHelper

    @SuppressLint("ClickableViewAccessibility")
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
        hasil = findViewById(R.id.hasil_klassifikasi)
        textBantuan = findViewById(R.id.textBantuan)

        user = intent.getParcelableExtra<User>(EXTRA_USER) as User

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

        // Open database
        getHelper = UserDataHelper.getInstance(applicationContext)
        getHelper.open()

        // Update Location
        val cursor = getHelper.queryById(user.username.toString())
        if (cursor.count > 0) {
            users = MappingHelper.mapCursorToObject(cursor)
            findViewById<TextView>(R.id.Name).text = users.name.toString()
            textLocation.text = users.location.toString()
            count = users.counter!!.toInt()
        }

        // Recording and Sentiment Analysist
        mediaRecorder = MediaRecorder()
        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(AudioFormat.ENCODING_PCM_16BIT)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setAudioChannels(1)
        mediaRecorder?.setAudioEncodingBitRate(128000)
        mediaRecorder?.setAudioSamplingRate(48000)

        btnSOS.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    val anim = AnimationUtils.loadAnimation(this, R.anim.fade_out)
                    recordNotif.startAnimation(anim)
                    stopRecording()
                    hasil.text = "Fisik"
                    hasil.visibility = View.VISIBLE
                    textBantuan.visibility = View.VISIBLE
                    textRecord.hint = "Hasil rekaman akan muncul disini"
                    hasil.hint = "Fisik"
                    recordNotif.visibility = View.GONE

                }

                MotionEvent.ACTION_DOWN -> {
                    val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
                    recordNotif.startAnimation(anim)
                    recordNotif.visibility = View.VISIBLE
                    hasil.setText("")

                    output = "${externalCacheDir?.absolutePath}/audiorecordtest$count.wav"
                    mediaRecorder?.setOutputFile(output)
                    startRecording()
                    count +=1
                    val values = ContentValues()
                    values.put(DataUserEntity.UserColumns.COUNTER, count.toString())
                    getHelper.update(user.username.toString(),values)

                    textRecord.setText("")
                    textRecord.hint = "Listening..."
                }
            }
            false
        })

    }

    private fun locationListener() = object :LocationListener{
        override fun onLocationChanged(location: Location) {
            val gc = Geocoder(this@MainActivity,Locale.getDefault())
            val addresses = gc.getFromLocation(location.latitude,location.longitude,1)
            val address = addresses.get(0).getAddressLine(0)
            textLocation.text = (""+address)

            val values = ContentValues()
            values.put(DataUserEntity.UserColumns.LOCATION, textLocation.text as String)
            getHelper.update(user.username.toString(),values)

            Toast.makeText(this@MainActivity, "Location Updated", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startRecording() {
        try {
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            state = true
            Toast.makeText(this, "Recording started!", Toast.LENGTH_SHORT).show()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun stopRecording(){
        if(state){
            mediaRecorder?.stop()
            mediaRecorder?.release()
            state = false
        }else{
            Toast.makeText(this, "You are not recording right now!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.profile_pic->{
                getHelper.close()
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
                getHelper.close()
                val moveWithObjectIntent = Intent(this@MainActivity, formActivity::class.java)
                moveWithObjectIntent.putExtra(formActivity.EXTRA_USER, users)
                startActivity(moveWithObjectIntent)
            }
            R.id.btn_history->{
                getHelper.close()
                startActivity(Intent(this, HistoryActivity::class.java))
            }
            R.id.btn_log_out->{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Log Out")
                builder.setMessage("Anda yakin ingin keluar?")
                builder.setPositiveButton("Yes"){dialogInterface, which ->
                    getHelper.close()
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

package com.latihan.zupaapps

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import org.w3c.dom.Text
import java.time.format.DateTimeFormatter
import java.util.*

class formActivity: AppCompatActivity(), View.OnClickListener{


    private lateinit var mLokasiKejadian: TextInputEditText
    private lateinit var mTanggalKejadian: TextInputEditText
    private lateinit var mWaktuKejadian: TextInputEditText
    private lateinit var mTipeKejahatan: AutoCompleteTextView
    private lateinit var mDetailKejahatan: TextInputEditText

    private lateinit var lLokasiKejadian: TextInputLayout
    private lateinit var lTanggalKejadian: TextInputLayout
    private lateinit var lWaktuKejadian: TextInputLayout
    private lateinit var lTipeKejadian: TextInputLayout
    private lateinit var lDetailKejadian: TextInputLayout

    private lateinit var btnUpload: Button
    private lateinit var btnSend: Button
    private lateinit var bckButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        //INISIALISASI
        lTanggalKejadian = findViewById(R.id.form_tanggal)
        lWaktuKejadian = findViewById(R.id.form_waktu)
        lTipeKejadian = findViewById(R.id.form_kejadian)

        lTipeKejadian.setOnClickListener(this)

        mTanggalKejadian = findViewById(R.id.text_input_form_tanggal)
        mWaktuKejadian = findViewById(R.id.text_input_form_waktu)

        btnSend = findViewById(R.id.button_send)
        btnSend.setOnClickListener(this)

        bckButton = findViewById(R.id.back_button_form)
        bckButton.setOnClickListener(this)

        btnUpload = findViewById(R.id.button_form_upload)
        btnUpload.setOnClickListener(this)

        //DATE PICKER TANGGAL KEJADIAN
        val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Pilih Tanggal")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
        mTanggalKejadian.setKeyListener(null)
        mTanggalKejadian.setOnClickListener {
            datePicker.show(supportFragmentManager, "DATE_PICKER")
        }
        lTanggalKejadian.setEndIconOnClickListener {
            datePicker.show(supportFragmentManager, "DATE_PICKER")
        }
        datePicker.addOnPositiveButtonClickListener {
            mTanggalKejadian.setText(""+datePicker.headerText)
        }

        //TIME PICKER WAKTU KEJADIAN
        val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(0)
                .setMinute(0)
                .setTitleText("Waktu Kejadian")
                .build()
        mWaktuKejadian.setKeyListener(null)
        mWaktuKejadian.setOnClickListener {
            timePicker.show(supportFragmentManager, "TIME_PICKER")
        }
        lWaktuKejadian.setEndIconOnClickListener {
            timePicker.show(supportFragmentManager, "TIME_PICKER")
        }
        timePicker.addOnPositiveButtonClickListener{
            mWaktuKejadian.setText(""+timePicker.hour+":"+timePicker.minute)
        }


        //EXPOSED DROPDOWN MENU TIPE KEJAHATAN
        val tipe = resources.getStringArray(R.array.tipeKejahatan)
        val adapter = ArrayAdapter(this, R.layout.list_kejahatan, tipe)
        (lTipeKejadian.editText as? AutoCompleteTextView)?.setAdapter(adapter)

    }

    private fun validateLokasi():Boolean{
        mLokasiKejadian = findViewById(R.id.text_input_form_lokasi)
        lLokasiKejadian = findViewById(R.id.form_lokasi)

        val currentLokasi:String = mLokasiKejadian.text.toString()

        if (currentLokasi.isEmpty()){
            lLokasiKejadian.setError("Lokasi tidak boleh kosong")
            return false
        }
        else {
            lLokasiKejadian.setError(null)
            return true
        }
        return true
    }

    private fun validateTanggal():Boolean{
        val currentTanggal:String = mTanggalKejadian.text.toString()

        if (currentTanggal.isEmpty()){
            lTanggalKejadian.setError("Tanggal tidak boleh kosong")
            return false
        }
        else {
            lTanggalKejadian.setError(null)
            return true
        }
        return true
    }

    private fun validateWaktu():Boolean{
        val currentWaktu:String = mWaktuKejadian.text.toString()

        if (currentWaktu.isEmpty()){
            lWaktuKejadian.setError("Waktu tidak boleh kosong")
            return false
        }
        else {
            lWaktuKejadian.setError(null)
            return true
        }
        return true
    }

    private fun validateTipe():Boolean{
        mTipeKejahatan = findViewById(R.id.text_input_form_kejadian)

        val currentTipe:String = mTipeKejahatan.text.toString()

        if (currentTipe.isEmpty()){
            lTipeKejadian.setError("Tipe tidak boleh kosong")
            return false
        }
        else {
            lTipeKejadian.setError(null)
            return true
        }
        return true
    }

    private fun validateDetail():Boolean{
        mDetailKejahatan = findViewById(R.id.text_input_form_detail)
        lDetailKejadian = findViewById(R.id.form_detail)

        val currentDetail:String = mDetailKejahatan.text.toString()

        if (currentDetail.isEmpty()){
            lDetailKejadian.setError("Lokasi tidak boleh kosong")
            return false
        }
        else {
            lDetailKejadian.setError(null)
            return true
        }
        return true
    }


    override fun onClick(v: View) {
        when(v.id){
            R.id.back_button_form->{
                finish()
            }
            R.id.button_send -> {
                if(!validateLokasi() or !validateTanggal() or !validateWaktu() or !validateTipe() or !validateDetail())
                    return
                else{
                    Toast.makeText(this@formActivity, "form berhasil dikirim", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
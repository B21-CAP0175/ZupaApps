package com.latihan.zupaapps

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.time.format.DateTimeFormatter
import java.util.*

class formActivity: AppCompatActivity(), View.OnClickListener{


    private lateinit var mLokasiKejadian: TextInputEditText
    private lateinit var mTanggalKejadian: TextInputEditText
    private lateinit var mWaktuKejadian: TextInputEditText
    private lateinit var mTipeKejahatan: AutoCompleteTextView
    private lateinit var mDetailKejahatan: TextInputEditText

    private lateinit var lTanggalKejadian: TextInputLayout
    private lateinit var lWaktuKejadian: TextInputLayout
    private lateinit var lTipeKejadian: TextInputLayout

    private lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        //INISIALISASI
        mLokasiKejadian = findViewById(R.id.text_input_form_lokasi)
        mTanggalKejadian = findViewById(R.id.text_input_form_tanggal)
        mWaktuKejadian = findViewById(R.id.text_input_form_waktu)
        mTipeKejahatan = findViewById(R.id.text_input_form_kejadian)
        mDetailKejahatan = findViewById(R.id.text_input_form_detail)

        lTanggalKejadian = findViewById(R.id.form_tanggal)
        lWaktuKejadian = findViewById(R.id.form_waktu)
        lTipeKejadian = findViewById(R.id.form_kejadian)

        lTipeKejadian.setOnClickListener(this)

        btnSend = findViewById(R.id.button_send)
        btnSend.setOnClickListener(this)

        //DATE PICKER TANGGAL KEJADIAN
        val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Pilih Tanggal")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
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


    override fun onClick(v: View) {
        when(v.id){
            R.id.form_tanggal -> {

            }

        }
    }
}
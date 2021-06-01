package com.latihan.zupaapps

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class HistoryActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var backBtn:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        backBtn = findViewById(R.id.back_button_history)
        backBtn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.back_button_history->{
                finish()
            }
        }
    }
    // TODO: DISESUAIKAN AJA NANTI LAYOUTNYA SAMA KONSEP LU SAT
}
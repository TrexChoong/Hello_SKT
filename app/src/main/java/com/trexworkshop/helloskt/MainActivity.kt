package com.trexworkshop.helloskt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Load UI to Memory
        // R = resouce class
        setContentView(R.layout.activity_main)

        //Write UI handler here
        //Value declaration; 1) val = value, 2) var = variable
        var buttonToggleQr: TextView = findViewById(R.id.buttonToggleQr)
        var init = 0;
        val qrCode = findViewById<ImageView>(R.id.imageViewQrCode)
        val buttonToggle = findViewById<Button>(R.id.buttonToggleQr)

        buttonToggle.setOnClickListener{
            if(init==0){
                buttonToggleQr.setText(R.string.hide_button)
                qrCode.visibility = View.VISIBLE
                init = 1
            } else if(init == 1){
                buttonToggleQr.setText(R.string.show_button)
                qrCode.visibility = View.INVISIBLE
                init = 0
            }
        }
    }


}
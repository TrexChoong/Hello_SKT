package com.trexworkshop.helloskt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trexworkshop.helloskt.databinding.ActivityMainLab3Binding

class MainActivityLab3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainLab3Binding =
            ActivityMainLab3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.radioGroupGender.check(binding.radioButtonMale.id)
        binding.buttonCalculate.setOnClickListener {
            var basic: Int = 0
            var extra: Int = 0
            var total: Int = 0
            val age = binding.spinnerAge.selectedItemPosition
            if (age == 0) {// Less than 17
                basic = 60
            } else if (age == 1){
                basic = 70
            }else if (age == 2){
                basic = 90
            }else if (age == 3){
                basic = 120
            }else if (age == 4){
                basic = 150
            }else if (age == 5){
                basic = 150
            } else {
                basic = -1
            }

            val gender = binding.radioGroupGender.checkedRadioButtonId
            if (gender == binding.radioButtonMale.id) {
                when (basic){
                    60 -> extra = 0
                    70 -> extra = 50
                    90 -> extra = 100
                    120 -> extra = 150
                    150 -> extra = 200
                    else -> { // Note the block
                        extra = -1
                    }
                }
            }

            val smoker = binding.checkBoxSmoker.isChecked
            if(smoker == true){
                when (age){
                    0 -> extra += 0
                    1 -> extra += 100
                    2 -> extra += 150
                    3 -> extra += 200
                    4 -> extra += 250
                    5 -> extra += 300
                    else -> { // Note the block
                        extra = -1
                    }
                }
            }
            total = basic + extra
            val premium: Premium = Premium(basic, extra, total)
            binding.myPremium = premium
        }
        binding.buttonReset.setOnClickListener {
            binding.myPremium = Premium(0,0,0)
            binding.spinnerAge.setSelection(0)
            binding.radioGroupGender.check(binding.radioButtonMale.id)
            binding.checkBoxSmoker.isChecked = false
        }
    }
}
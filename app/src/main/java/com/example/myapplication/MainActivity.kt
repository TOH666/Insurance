package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var myData: PremiumModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myData = ViewModelProviders.of(this).get(PremiumModel::class.java)
        display()

        buttonCalculate.setOnClickListener {
            if(radioGroup.checkedRadioButtonId != -1){
                myData.premiumAmount = getPremium()
                display()
            }else{
                val toast:Toast = Toast.makeText(this,"Please select the age range", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0,0)
                toast.show()
            }
        }

        buttonReset.setOnClickListener {
            spinnerAge.setSelection(0)
            radioGroup.clearCheck()
            checkBoxSmoker.setChecked(false)
            textViewPayment.setText("")
            myData.premiumAmount = 0.0
        }
    }

    fun getPremium():Double{
        return when(spinnerAge.selectedItemPosition){
            0 -> 60.00
            1 -> 70.00 + (if (RBMale.isChecked)50.00 else 0.00) + (if (checkBoxSmoker.isChecked)100.00 else 0.00)
            2 -> 90.00 + (if (RBMale.isChecked)100.00 else 0.00) + (if (checkBoxSmoker.isChecked)150.00 else 0.00)
            3 -> 120.00 + (if (RBMale.isChecked)150.00 else 0.00) + (if (checkBoxSmoker.isChecked)200.00 else 0.00)
            4 -> 150.00 + (if (RBMale.isChecked)200.00 else 0.00) + (if (checkBoxSmoker.isChecked)250.00 else 0.00)
            else -> 150.00 + (if (RBMale.isChecked)200.00 else 0.00) + (if (checkBoxSmoker.isChecked)300.00 else 0.00)
        }
    }

    fun display(){
        if(myData.premiumAmount != 0.0){
            textViewPayment.text = myData.premiumAmount.toString()
        }
    }
}

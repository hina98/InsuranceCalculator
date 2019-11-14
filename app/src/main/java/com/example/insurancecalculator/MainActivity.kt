package com.example.insurancecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedItem = spinnerAge.getItemAtPosition(position)
        //val selectedItem = spinnerAge.selectedItemPosition
        Toast.makeText(this,"Selected Item =" + selectedItem, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //handling item selected listener for spinner
        spinnerAge.onItemSelectedListener = this

        buttonCalculate.setOnClickListener{
            calculatePremium()
        }

        /*val buttonThird: Button
        buttonThird.setOnClickListener(this)*/
    }

    private fun calculatePremium()
    {
        //get the age group. Position of the array
        val age: Int = spinnerAge.selectedItemPosition

        var premium = when(age)
        {
            0 -> 60 //less than 17
            1 -> 70 //17 to 25
            2 -> 90 //26 to 30
            3 -> 120 //31-40
            4 -> 150 //41-55
            else -> 150
        }


        //get the gender selection. ID of the radio button
        val gender: Int = radioGroupGender.checkedRadioButtonId
        if(gender == R.id.radioButtonMale)
        {
            //calculate premium for male
            var extraPayment = when(age)
            {
                0 -> 0 //less than 17
                1 -> 50 //17 to 25
                2 -> 100 //26 to 30
                3 -> 150 //31-40
                4 -> 200 //41-55
                else -> 200
            }
            premium += extraPayment
        }
        else
        {
            //calculate premium for female
            //premium
        }

        //determine smoker or non-smoker
        val smoker: Boolean = checkBoxSmoker.isChecked
        if(smoker)
        {
            //calculate premium for smoker
            var extraPaymentSmoker = when(age)
            {
                0 -> 0 //less than 17
                1 -> 100 //17 to 25
                2 -> 150 //26 to 30
                3 -> 200 //31-40
                4 -> 250 //41-55
                else -> 300
            }
            premium += extraPaymentSmoker
        }

        val symbol = Currency.getInstance(Locale.getDefault()).symbol
        textViewPremium.text = String.format("%s %d", symbol, premium)


    }

    fun reset(view: View)
    {
        textViewPremium.text = getString(R.string.insurance_premium)
        checkBoxSmoker.isChecked = false
        radioButtonFemale.isChecked = false
        radioButtonMale.isChecked = false
        spinnerAge.setSelection(0)
    }
}

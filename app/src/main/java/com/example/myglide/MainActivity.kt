package com.example.myglide

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_calendar.setOnClickListener { view ->
            clickDatePicker(view)

        }
    }

    fun clickDatePicker(view: View) {

        val mycalendar = Calendar.getInstance()
        val year = mycalendar.get(Calendar.YEAR)
        val month = mycalendar.get(Calendar.MONTH)
        val day = mycalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener
            { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                Toast.makeText(
                    this,
                    "Choosen data is $selectedDate", Toast.LENGTH_LONG
                ).show()
                text_date.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/YYYY", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes = theDate!!.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToMinutes = currentDate!!.time / 60000

                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes
                // val numberOfDays = (differenceInMinutes/(1000*60*60*24))
                text_age_in_seconds.setText(differenceInMinutes.toString())
            }, year
            , month
            , day
        )

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }

}
// var timeClicked = 0
//        button.setOnClickListener{
//            textView.text = timeClicked++.toString()
//            Toast.makeText(this,"Hi Tejal!",Toast.LENGTH_LONG).show()
//        }
package com.example.datepickerdialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  val c = Calendar.getInstance()
        var year :Int
        var month :Int
        var day :Int

        btnFecha.setOnClickListener {

            if(txvFecha.text.toString() == "Fecha"){
                val c = Calendar.getInstance()
                 year = c.get(Calendar.YEAR)
                 month = c.get(Calendar.MONTH)
                 day = c.get(Calendar.DAY_OF_MONTH)
            }else{
                val myDate = txvFecha.text.toString()
                val formatter = DateTimeFormatter.ofPattern("yyyy/MM/d")
                val date = (LocalDate.parse(myDate,formatter))


                year= date.year
                month = date.monthValue -1
                day = date.dayOfMonth


            }




            var dpd = DatePickerDialog(this@MainActivity,DatePickerDialog.OnDateSetListener{ datePicker: DatePicker, y: Int, m: Int, d: Int ->
                //meses
                var mes= (m + 1)
                var mesString =""
                if(mes in 1..9){
                    mesString = ("0")+mes.toString()
                }else{
                    mesString = mes.toString()
                }

                //dias
                var dayString = if(d !in 1..9){
                    d.toString()
                }else{
                     "0$d"
                }


                txvFecha.setText("" + y + "/" + mesString + "/" + d)
            },year,month,day)
            dpd.show()
        }


        btnHora.setOnClickListener {
            var hoursOfDay:Int 
            var minute:Int

            if(txvHora.text.toString() =="Hora"){
                val c = Calendar.getInstance()
                    hoursOfDay = c.get(Calendar.HOUR_OF_DAY)
                    minute = c.get(Calendar.MINUTE)



            }else{
                val myTime = txvHora.text.toString()
                val formatter = DateTimeFormatter.ofPattern("H:mm")
                val time = LocalTime.parse(myTime,formatter)
                hoursOfDay = time.hour
                minute = time.minute
            }


            var tid = TimePickerDialog(this@MainActivity,TimePickerDialog.OnTimeSetListener { timePicker:TimePicker, h:Int, m:Int ->

                val hourString = if(h !in 1..9){
                    h.toString()
                }else{
                    "0$h"
                }
                val minuteString = if(m !in 1..9){
                    m.toString()
                }else{
                    "0$m"
                }


                txvHora.setText("$hourString:$minuteString")


            },hoursOfDay,minute, false)
            tid.show()

        }
    }
}

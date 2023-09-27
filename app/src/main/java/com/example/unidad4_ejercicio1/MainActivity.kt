package com.example.unidad4_ejercicio1

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import org.w3c.dom.Text
import java.time.LocalDate

class MainActivity : AppCompatActivity(),OnClickListener {
    private var mYear=0
    private var mMonth:Int = 0
    private var mDay:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fechaActual : TextView = findViewById(R.id.tVFechaActual)
        val hoy =LocalDate.now()
        fechaActual.setText("Hoy es: $hoy" )

        val boton=findViewById<Button>(R.id.btnFecha)
        val c = Calendar.getInstance()
        mYear = c[Calendar.YEAR]
        mMonth = c[Calendar.MONTH]
        mDay = c[Calendar.DAY_OF_MONTH]
        boton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        // Lanzar el DatePickerDialog
        val c = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(this,
            object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(
                    view: DatePicker, year: Int,
                    monthOfYear: Int, dayOfMonth: Int
                ) {//Aqui hago el codigo del boton
                    //Meto los valores para las fecha del calendario
                    //Se asigna el valor para la fecha seleccionada
                    val fechaSeleccionada = LocalDate.of(year,monthOfYear+1, dayOfMonth)
                    //Asigno el valor para la fecha actual
                    val fechaActual = LocalDate.now()
                    //Calculo la diferencia entre las 2 fechas
                    val diferenciaFechas = fechaSeleccionada.until(fechaActual)
                    //Se declara el Text View en el que se reflejará y se ajustará su texto con año, mes y día
                    val edad : TextView = findViewById(R.id.tVFechaNac)
                    edad.setText("Tienes: ${diferenciaFechas.years} años, ${diferenciaFechas.months} meses, ${diferenciaFechas.days} dias")

                }}
            , mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }
}
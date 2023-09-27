package com.example.unidad4_ejercicio1

import android.annotation.SuppressLint
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
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity(),OnClickListener {
    private var mYear=0
    private var mMonth:Int = 0
    private var mDay:Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fechaActual : TextView = findViewById(R.id.tVFechaActual)
        val hoy =LocalDate.now()

        // Formateo la fecha actual hoy y la paso al formato "día/mes/año"
        val formatoCorrecto = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        fechaActual.text = "Hoy es ${hoy.format(formatoCorrecto)}"

        val boton=findViewById<Button>(R.id.btnFechaNac)
        val c = Calendar.getInstance()
        mYear = c[Calendar.YEAR]
        mMonth = c[Calendar.MONTH]
        mDay = c[Calendar.DAY_OF_MONTH]
        boton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        // Lanzo el DatePickerDialog

        val datePickerDialog = DatePickerDialog(this,
            { view, year, monthOfYear, dayOfMonth ->
                //Aqui hago el codigo del botón
                //Introduzco los valores para las fechas del calendario

                // Asigno el valor para la fecha seleccionada y le añado +1 al año porque va de 0 a 11
                val fechaSeleccionada = LocalDate.of(year, monthOfYear + 1, dayOfMonth)


                // Formateo la fecha seleccionada en el formato "día/mes/año"
                val formatoCorrecto = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                val fechaSeleccionadaFormateada = fechaSeleccionada.format(formatoCorrecto)


                // Asigno el valor para la fecha actual con LocalDate.now()
                val fechaActual = LocalDate.now()


                // Calculo la diferencia entre las 2 fechas (actual - nacimiento)
                val diferenciaFechas = fechaSeleccionada.until(fechaActual)

                // Declaro los TextViews en los que se mostrará la información de cada uno de ellos
                val fechaNac: TextView = findViewById(R.id.tVNaciste)
                val edad: TextView = findViewById(R.id.tVCalcularEdad)

                // Actualizo los TextViews
                fechaNac.text = "Naciste el $fechaSeleccionadaFormateada"
                edad.text = "Tienes ${diferenciaFechas.years} años, ${diferenciaFechas.months} meses y ${diferenciaFechas.days} días"
            }
            , mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }
}
package com.example.calculadora

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class  CalculatorActivity : AppCompatActivity() {

    private var number1: Double = 0.0
    private var number2: Double = 0.0
    private var result: Double = 0.0
    private lateinit var textResult: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.calculator_main)

        textResult = findViewById(R.id.result)

        val emailRecibido = intent.getStringExtra("email") ?: "No email provided"

        // Por ejemplo, mostrarlo en un TextView
        val textEmail: TextView = findViewById(R.id.userEmail)
        textEmail.text = "Email: $emailRecibido"

        val sum: Button = findViewById(R.id.sum)
        sum.setOnClickListener{
            actualizarNumeros()
            sum()
        }

        val subtraction: Button = findViewById(R.id.subtraction)
        subtraction.setOnClickListener{
            actualizarNumeros()
            subtraction()
        }

        val multiplication: Button = findViewById(R.id.multiplication)
        multiplication.setOnClickListener{
            actualizarNumeros()
            multiplication()
        }

        val division: Button = findViewById(R.id.division)
        division.setOnClickListener{
            actualizarNumeros()
            division()
        }

        val logOut: Button = findViewById(R.id.returnToLogin)
        logOut.setOnClickListener{
            logOut()
        }
    }

    fun actualizarNumeros(){
        var campo1 = findViewById<EditText>(R.id.number1)
        var campo2 = findViewById<EditText>(R.id.number2)

        number1 = campo1.text.toString().toDoubleOrNull() ?: 0.0
        number2 = campo2.text.toString().toDoubleOrNull() ?: 0.0
    }

    fun sum(){
        result = number1 + number2
        textResult.text = "Resultado: $result"
    }

    fun subtraction(){
        result = number1 - number2
        textResult.text = "Resultado: $result"
    }

    fun multiplication(){
        result = number1 * number2
        textResult.text = "Resultado: $result"
    }

    private fun division(){
        if (number2 != 0.0) {
            result = number1 / number2
            textResult.text = "Resultado: $result"
        } else {
            textResult.text = "No se puede dividir por cero"
        }
    }

    private fun logOut(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
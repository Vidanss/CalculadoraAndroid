package com.example.calculadora

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var loginbutton:Button=findViewById(R.id.button_login)
        loginbutton.setOnClickListener{login()}
    }

    private fun login(){
        var email=findViewById<EditText>(R.id.email_entry).text.toString()
        var password=findViewById<EditText>(R.id.password_entry).text.toString()

        // para pruebas
        var emailPruebas = "user"
        var passwordPruebas = "Pass_12345"

        // 1. Validar que los campos no estén vacíos
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Por favor ingrese el email y la contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        // --- 2. Validar la contraseña según las reglas (Optimizado con lista) ---

        // Definimos las reglas como pares de (condición, mensaje de error)
        val validationRules = listOf(
            { pwd: String -> pwd.length >= 9 } to "La contraseña debe tener al menos 9 caracteres",
            { pwd: String -> pwd.contains(Regex("[A-Z]")) } to "La contraseña debe contener al menos una letra mayúscula",
            { pwd: String -> pwd.contains(Regex("[a-z]")) } to "La contraseña debe contener al menos una letra minúscula",
            { pwd: String -> pwd.contains(Regex("[0-9]")) } to "La contraseña debe contener al menos un número",
            { pwd: String -> pwd.contains("_") } to "La contraseña debe contener al menos un guión bajo (_)"
        )

        //iterar sobre las reglas
        for ((condition, errorMessage) in validationRules) {
            if (!condition(password)) { // Si la condición no se cumple...
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show() // ...mostramos el error
                return // ...y salimos de la función
            }
        }


        if (emailPruebas == email && passwordPruebas == password) {
            val sendMessage = Intent(this, CalculatorActivity::class.java)
            sendMessage.putExtra("email", email)
            startActivity(sendMessage)
            // Opcional: finish()
        } else {
            // Credenciales incorrectas (solo si pasaron las validaciones de formato)
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
        }
    }
}
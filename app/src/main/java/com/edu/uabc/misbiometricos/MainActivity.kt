package com.edu.uabc.misbiometricos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_main.*

import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val executor = Executors.newSingleThreadExecutor()
        val activity: FragmentActivity = this // reference to activity

        val biometricPrompt = BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    mostrarTexto("PROBLEMAS")

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    mostrarTexto("SI ERES TU!!!!!")
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    mostrarTexto("NO ERES TU!!!!!")
                }
        })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Titulo a mostrrar")
            .setSubtitle("Sbubtitulo a mostrar")
            .setDescription("Descripcion a mostrar")
            .setNegativeButtonText("Texto de negativa o (cancelacion)")
            .build()

        biometricBttn.setOnClickListener { view ->

            biometricPrompt.authenticate(promptInfo)

        }

    }

    fun mostrarTexto(texto:String){
        runOnUiThread{
            Toast.makeText(applicationContext, texto, Toast.LENGTH_SHORT)
        }
    }
}

package com.celsoluiz73.calculadoraimc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.celsoluiz73.calculadoraimc.databinding.ActivityMainBinding
import java.text.DecimalFormat
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val botaoCalcular = binding.btnCalcular
        val mensagem = binding.txtMensagem

        botaoCalcular.setOnClickListener {
            val valorPeso = binding.etxPeso.text.toString()
            val valorAltura = binding.etxAltura.text.toString()

            if (valorPeso.isEmpty()) {
                mensagem.setText("Por favor informe o seu Peso")
            } else if (valorAltura.isEmpty()) {
                mensagem.setText("Por favor informe sua Altura")
            } else {
                calcularIMC()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun calcularIMC() {

        val pesoId = binding.etxPeso
        val alturaId = binding.etxAltura

        val peso = Integer.parseInt(pesoId.text.toString())
        val altura = java.lang.Float.parseFloat(alturaId.text.toString())
        val resultado = binding.txtMensagem
        var imc = peso / (altura * altura)

        if (imc > 0.0 && imc <= 18.5) {
            imc.toString()
            resultado.setText("IMC: ${imc.roundToInt()} \n Peso Baixo")
        } else if (imc > 18.6 && imc <= 24.9) {
            imc.toString()
            resultado.setText("IMC: ${imc.roundToInt()} \n Peso Normal")
        } else if (imc > 25.0 && imc <= 29.9) {
            imc.toString()
            resultado.setText("IMC: ${imc.roundToInt()} \n Sobrepeso")
        } else if (imc > 30.0 && imc <= 34.9) {
            imc.toString()
            resultado.setText("IMC: ${imc.roundToInt()} \n Obesidade (Grau I)")
        } else if (imc > 35.0 && imc <= 39.9) {
            imc.toString()
            resultado.setText("IMC: ${imc.roundToInt()} \n Obesidade Severa (Grau II)")
        } else if (imc > 40.0) {
            imc.toString()
            resultado.setText("IMC: ${imc.roundToInt()/100.0} \n Obesidade Mórbita (Grau III)")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflar = menuInflater
        inflar.inflate(R.menu.menu_principal,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.reset -> {

                val limparEditPeso = binding.etxPeso
                val limparEditAltura = binding.etxAltura
                val limparMensagem = binding.txtMensagem

                limparEditPeso.setText("")
                limparEditAltura.setText("")
                limparMensagem.setText("")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
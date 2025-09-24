package com.example.consumerbank

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ispbTextView = findViewById<TextView>(R.id.ispbTv)
        val nameTextView = findViewById<TextView>(R.id.nameTv)
        val codeTextView = findViewById<TextView>(R.id.codeTv)
        val fullNameTextView = findViewById<TextView>(R.id.fullNameTv)

        val id = intent.getStringExtra("ID_EXTRA")

        if (id != null) {
            val brasilApi = RetrofitHelper.getInstance().create(BrasilApi::class.java)

            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val response = brasilApi.getBank(id)
                    if (response.isSuccessful) {
                        val bank = response.body()
                        Log.d("Retorno da API: ", bank.toString())

                        withContext(Dispatchers.Main) {
                            ispbTextView.text = "ISPB: ${bank?.ispb}"
                            nameTextView.text = "NOME: ${bank?.name}"
                            codeTextView.text = "CODE: ${bank?.code.toString()}"
                            fullNameTextView.text = "NOME COMPLETO:\n ${bank?.name}"
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            ispbTextView.text = "Erro: ${response.code()}"
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        ispbTextView.text = "Ocorreu um erro: ${e.message}"
                    }
                }
            }
        } else {
            ispbTextView.text = "CEP não encontrado."
        }
    }
}
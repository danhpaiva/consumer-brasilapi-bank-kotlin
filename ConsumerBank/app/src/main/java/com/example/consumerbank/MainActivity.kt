package com.example.consumerbank

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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val idBankEditText = findViewById<EditText>(R.id.editTextId)
        val searchButton = findViewById<Button>(R.id.buttonFetchBanks)

        searchButton.setOnClickListener {
            val id = idBankEditText.text.toString().trim()

            if (id.isNotEmpty()) {
                val intent = Intent(this, ResultadoActivity::class.java)

                intent.putExtra("ID_EXTRA", id)

                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, digite um Id.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
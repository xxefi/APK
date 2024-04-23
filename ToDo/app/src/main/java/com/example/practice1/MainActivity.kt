package com.example.practice1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
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

        //////////////////////////////////////////////////////////////////////////////////////////////


        val listView = findViewById<ListView>(R.id.listView);
        val userData: EditText = findViewById(R.id.user_data);
        val button: Button = findViewById(R.id.button);

        var todos: MutableList<String> = mutableListOf()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, todos);
        listView.adapter = adapter;

        listView.setOnItemClickListener { adapterView, view, i, l ->
            val text = listView.getItemAtPosition(i).toString();
            adapter.remove(text);

            Toast.makeText(this, "Удалена задача: $text", Toast.LENGTH_LONG).show();
        }

        button.isEnabled = true;

        button.setOnClickListener{
          val text = userData.text.toString().trim();
            if (text != ""){
                adapter.insert(text, 0);
            }
            else {
                Toast.makeText(this, "Вы ничего не ввели", Toast.LENGTH_LONG).show();
            }
            userData.text.clear();
        };
    }
}
package com.example.ganesh.expenseshare

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title="Expense Splitter"

        val button = findViewById<Button>(R.id.buttonEditMembers)
        button.setOnClickListener{
            var addIntent = Intent(this,AddPeople::class.java)
            startActivity(addIntent)

        }


        val expbutton = findViewById<Button>(R.id.buttonAddExpense)
        expbutton.setOnClickListener{
            var addIntent = Intent(this,AddExpense::class.java)
            startActivity(addIntent)

        }

    }
}

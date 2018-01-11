package com.example.ganesh.expenseshare

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add_expense.*
import java.util.*

class AddExpense : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        title="Add Expense"

        var expensemodel = ExpenseModel()


        var realm = Realm.getDefaultInstance()
        val query = realm.where(Member ::class.java)

        var results = query.findAll()

        var spinner = findViewById<Spinner>(R.id.spinnerName)

        val arrayadapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,results)

        spinner!!.setAdapter(arrayadapter)


        var buttonaddExpense = findViewById<Button>(R.id.buttonSaveExpense)

        buttonaddExpense.setOnClickListener{

            var expense = Expense(_ID= UUID.randomUUID().toString(),name = "name",amount=0.0,dateOfExpense = null,expenseDetails = "test")


            expensemodel.addExpense(realm,expense)
            println("expense added success")


            var results = expensemodel.getExpenses(realm)

            println("size ${results.count()}")

            println(results[0]!!.name);




        }


        }



    }


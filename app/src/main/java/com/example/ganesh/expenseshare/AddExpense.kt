package com.example.ganesh.expenseshare

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add_expense.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
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
        //Date field

        val textView = findViewById<TextView>(R.id.editDate)

        textView.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())


        var cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            textView.text = sdf.format(cal.time)
            println("hello $cal.getTime()")

        }

        textView.setOnClickListener {
            DatePickerDialog(this@AddExpense, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }


        var buttonaddExpense = findViewById<Button>(R.id.buttonSaveExpense)

        buttonaddExpense.setOnClickListener{

            var personName: String = spinner.selectedItem.toString()

          //  val numberFormat = DecimalFormat("#.00")
            println("test for double ${editTextAmount.text.toString().toDouble()}" )

           // println("amount value ${editTextAmount.toString().toDouble()}")


            var expense = Expense(_ID= UUID.randomUUID().toString(),name = personName,amount=editTextAmount.text.toString().toDouble(),dateOfExpense = cal.time,expenseDetails = editDetails.toString())
            expensemodel.addExpense(realm,expense)
            println("expense added success")


            var results = expensemodel.getExpenses(realm)

           println("size ${results.count()}")

            println(personName);




        }


        }



    }


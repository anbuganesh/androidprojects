package com.example.ganesh.expenseshare

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import io.realm.Realm
import org.w3c.dom.Text
import java.text.DecimalFormat

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

        var realm = Realm.getDefaultInstance()

        var expensemodel = ExpenseModel()

        var totalexpense : Double = expensemodel.getExpenses(realm).sum("amount").toDouble()

        val totExpense: TextView = findViewById<TextView>(R.id.textTotalExpenseValue)

        totExpense.text = totalexpense.toString()


        val query = realm.where(Member ::class.java)

        val results = query.findAll().size.toDouble()

        var perhead :Double = totalexpense / results

        val df = DecimalFormat("#.##")

        var perheadtxt = findViewById<TextView>(R.id.textExpPerHeadVal)

        perheadtxt.text = df.format(perhead).toString()



        val expbutton = findViewById<Button>(R.id.buttonAddExpense)
        expbutton.setOnClickListener{
            var addIntent = Intent(this,AddExpense::class.java)
            startActivity(addIntent)

        }

        var viewexpbutton = findViewById<Button>(R.id.buttonViewExpense)

        viewexpbutton.setOnClickListener{

            var viewIntent = Intent(this,ViewExpense::class.java)
            startActivity(viewIntent)
        }

    }


}

package com.example.ganesh.expenseshare

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import io.realm.Realm
import java.text.SimpleDateFormat
import java.util.*

class ViewExpense : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_expense)

        title = "View Expense"



        val listItem = findViewById<ListView>(R.id.ListViewExpenseView)

       // val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,results)

       // listItem.adapter = adapter

        listItem.adapter = customAdapter(this)




    }

    private class customAdapter(context: Context): BaseAdapter() {



        var realm = Realm.getDefaultInstance()

        var expensemodel = ExpenseModel()

        var results = expensemodel.getExpenses(realm)

        // println("size view expense ${results.count()}")
        // println(" test  ${results[4]!!.name}  ${results[4]!!.amount} ${results[4]!!.expenseDetails}")


        private val mContext: Context

        init {
            mContext = context
        }

        override fun getItem(p0: Int): Any {
            return "Test string"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return results.size
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

            val layoutinflator = LayoutInflater.from(mContext)
            val rowMain = layoutinflator.inflate(
                    R.layout.row_layout,
                    viewGroup,
                    false
            )

            val name = rowMain.findViewById<TextView>(R.id.textViewName)
            name.text = results[position]!!.name

            val descr = rowMain.findViewById<TextView>(R.id.textViewDescr)

            descr.text = results[position]!!.expenseDetails


            val expDate = rowMain.findViewById<TextView>(R.id.textViewDate)

            val cal: Date = results[position]!!.dateOfExpense as Date

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            expDate.text = sdf.format(cal.time)


            val expAmount = rowMain.findViewById<TextView>(R.id.textViewAmount)
            expAmount.text = results[position]!!.amount.toString()





            return rowMain
            // val textview = TextView(mContext)

            // textview.text = "List of values"

            //return textview

        }

    }
}



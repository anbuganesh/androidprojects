package com.example.ganesh.expenseshare

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import io.realm.Realm
import io.realm.internal.Table
import org.w3c.dom.Text
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Expense Splitter"

        var realm = Realm.getDefaultInstance()

        var expensemodel = ExpenseModel()

        var totalexpense: Double = expensemodel.getExpenses(realm).sum("amount").toDouble()

        val totExpense: TextView = findViewById<TextView>(R.id.textTotalExpenseValue)

        totExpense.text = totalexpense.toString()


        val query = realm.where(Member::class.java)

        val results = query.findAll().size.toDouble()


        if (results > 0 ) {

            var perhead: Double = totalexpense / results

            val df = DecimalFormat("#.##")

            var perheadtxt = findViewById<TextView>(R.id.textExpPerHeadVal)

            perheadtxt.text = df.format(perhead).toString()

        }


        val listItem = findViewById<ListView>(R.id.expSumListView)

        listItem.adapter = expSummarycustomAdapter(this)

        val button = findViewById<Button>(R.id.buttonEditMembers)
        button.setOnClickListener {
            var addIntent = Intent(this, AddPeople::class.java)
            startActivity(addIntent)

        }

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

    class expSummarycustomAdapter(context: Context): BaseAdapter() {

        val resultsSummary :ArrayList<ExpenseSummary> = getExpenseSummary();


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
            return resultsSummary.size

        }



        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

            val layoutinflator = LayoutInflater.from(mContext)
            val rowMain = layoutinflator.inflate(
                    R.layout.rowlayout_exp_summary,
                    viewGroup,
                    false
            )

            val name = rowMain.findViewById<TextView>(R.id.textViewSumName)
            name.text = resultsSummary[position]!!.name

            val descr = rowMain.findViewById<TextView>(R.id.textViewSumAmountSpent)

            descr.text = resultsSummary[position]!!.amountsepent.toDouble().toString()

            val expAmount = rowMain.findViewById<TextView>(R.id.textViewSumExcessDeficit)
            expAmount.text = resultsSummary[position]!!.excessShortfall.toString()

            return rowMain


        }

    }


}

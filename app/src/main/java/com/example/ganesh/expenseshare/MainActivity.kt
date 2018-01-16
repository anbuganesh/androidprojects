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

        val button = findViewById<Button>(R.id.buttonEditMembers)
        button.setOnClickListener {
            var addIntent = Intent(this, AddPeople::class.java)
            startActivity(addIntent)

        }

        var realm = Realm.getDefaultInstance()

        var expensemodel = ExpenseModel()

        var totalexpense: Double = expensemodel.getExpenses(realm).sum("amount").toDouble()

        val totExpense: TextView = findViewById<TextView>(R.id.textTotalExpenseValue)

        totExpense.text = totalexpense.toString()


        val query = realm.where(Member::class.java)

        val results = query.findAll().size.toDouble()

        var perhead: Double = totalexpense / results

        val df = DecimalFormat("#.##")

        var perheadtxt = findViewById<TextView>(R.id.textExpPerHeadVal)

        perheadtxt.text = df.format(perhead).toString()


        val listItem = findViewById<ListView>(R.id.expSumListView)

        listItem.adapter = expSummarycustomAdapter(this)




     /*   fun  fillCountryTable() {

            TableRow row;
            TextView t1, t2;
            //Converting to dip unit
             dip: Int = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            (float) 1, getResources().getDisplayMetrics());

            for (int current = 0; current < CountriesList.abbreviations.length; current++) {
            row = new TableRow(this);

            t1 = new TextView(this);
            t1.setTextColor(getResources().getColor(R.color.yellow));
            t2 = new TextView(this);
            t2.setTextColor(getResources().getColor(R.color.dark_red));

            t1.setText(CountriesList.abbreviations[current]);
            t2.setText(CountriesList.countries[current]);

            t1.setTypeface(null, 1);
            t2.setTypeface(null, 1);

            t1.setTextSize(15);
            t2.setTextSize(15);

            t1.setWidth(50 * dip);
            t2.setWidth(150 * dip);
            t1.setPadding(20*dip, 0, 0, 0);
            row.addView(t1);
            row.addView(t2);

            country_table.addView(row, new TableLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        }
*/


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
            // val textview = TextView(mContext)

            // textview.text = "List of values"

            //return textview

        }

    }


}

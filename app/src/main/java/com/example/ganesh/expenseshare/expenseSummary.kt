package com.example.ganesh.expenseshare

import android.widget.TextView
import io.realm.Realm
import java.text.DecimalFormat


/**
 * Created by ganesh on 16-01-2018.
 */
class ExpenseSummary(       var name: String = "",
               var amountsepent: Double = 0.00,
                            var excessShortfall: Double = 0.00
)

fun getExpenseSummary():  ArrayList<ExpenseSummary>
{


    var realm = Realm.getDefaultInstance()
    val queryMember = realm.where(Member ::class.java)

    var memberresults = queryMember.findAll()

    val membercnt = memberresults.size

    val queryTotalExpense = realm.where(Expense ::class.java)

    val TotalExpenseAmount = queryTotalExpense.findAll().sum("amount").toDouble()

    val expensePerHead = TotalExpenseAmount/membercnt

    val df = DecimalFormat("#.##")

  //  var perheadtxt = findViewById<TextView>(R.id.textExpPerHeadVal)

    val PerHead = df.format(expensePerHead).toString()



    var expSummaryList = ArrayList<ExpenseSummary>()




    for (membername in memberresults)
    {

        val expenseresults = realm.where(Expense::class.java).equalTo("name",membername.name).sum("amount").toDouble().toString()


                val expdiff = expenseresults.toDouble()-expensePerHead
                val expdiffStr = df.format(expdiff).toString()


        println("summary ${membername.name}  ${expenseresults} ${expdiffStr}")

        expSummaryList.add(ExpenseSummary(membername.name,expenseresults.toDouble(),expdiffStr.toDouble()))
    }





    //expSummaryList.add(ExpenseSummary("Abi",600.00,excessShortfall = -100.00))

   // expSummaryList.add(ExpenseSummary("Shyam",1300.00,excessShortfall = 600.00))

 return expSummaryList;
}



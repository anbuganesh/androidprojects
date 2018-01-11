package com.example.ganesh.expenseshare

import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by ganesh on 11-01-2018.
 */
open class Expense(        @PrimaryKey open var _ID: String = "",
                           open var name: String = "",
                           open var amount: Double = 0.0,
                           open var dateOfExpense: java.util.Date? = null,
                           open var expenseDetails: String = "") : RealmObject()
{
    fun copy(

            _ID: String = UUID.randomUUID().toString(),
            name: String = this.name,
            amount: Double = this.amount,
            dateOfExpense: java.util.Date? = this.dateOfExpense,
            expenseDetails: String = this.expenseDetails
    ) = Expense(_ID,name,amount,dateOfExpense,expenseDetails)

}


interface ExpenseInterface {
    fun addExpense(realm: Realm, expense: Expense): Boolean
    //fun delExpense(realm: Realm, _ID: Int): Boolean
    //fun editExpense(realm: Realm, expense: Expense): Boolean
    //fun getExpense(realm: Realm, _ID: Int): Expense
   // fun removeExpense(realm: Realm)
}

class ExpenseModel : ExpenseInterface {

    override fun addExpense(realm: Realm, expense: Expense): Boolean {
        try {
            realm.beginTransaction()
            realm.copyToRealm(expense)
            realm.commitTransaction()
            return true
         } catch (e: Exception) {
           println(e)
            realm.commitTransaction()

            return false
        }
    }

    fun getExpenses(realm: Realm): RealmResults<Expense> {
        return realm.where(Expense::class.java).findAll()
    }

}
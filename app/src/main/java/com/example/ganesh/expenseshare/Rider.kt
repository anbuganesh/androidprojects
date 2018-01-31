package com.example.ganesh.expenseshare

import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.PrimaryKey
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by ganesh on 28-01-2018.
 */

open class Rider
(       @PrimaryKey open var _ID: Int = 0,
        open var name: String = "",
        open var rideKm: Double = 0.0,
        open var rideAmount: Int = 0,
        open var rideDate: java.util.Date? = null) : RealmObject()

{
    fun copy(

            _ID: Int = 1,
            name: String = this.name,
            rideKm: Double = this.rideKm,
            rideAmount: Int = this.rideAmount,
            rideDate: java.util.Date? = this.rideDate
    ) = Rider(_ID,name,rideKm,rideAmount,rideDate)

}


interface RiderInterface {
    fun addRide(realm: Realm, rider: Rider): Boolean
    //fun delExpense(realm: Realm, _ID: Int): Boolean
    //fun editExpense(realm: Realm, expense: Expense): Boolean
    //fun getExpense(realm: Realm, _ID: Int): Expense
    // fun removeExpense(realm: Realm)
}


class RiderModel : RiderInterface {

    override fun addRide(realm: Realm, rider: Rider): Boolean {
        try {
            realm.beginTransaction()
            realm.copyToRealm(rider)
            realm.commitTransaction()
            return true
        } catch (e: Exception) {
            println(e)
            realm.commitTransaction()

            return false
        }
    }

    fun getRiders(realm: Realm): RealmResults<Rider> {
        return realm.where(Rider::class.java).findAll()
    }

    fun getRidersdummy(totfare:Int, totdist: Int, passengercnt: Int): ArrayList<Rider>{

        var riders : ArrayList<Rider> = ArrayList()


        var totalkm = totdist*passengercnt

        println("totalkm $totalkm  totfare $totfare")

        val perkm :Double = totfare.toDouble()/totalkm.toDouble()

        println("perkm $perkm")


        val fareperpassenger :Double  = perkm*totdist

        println("fareperpassenger $fareperpassenger")




        for (i in 1..passengercnt)
        {

            var rider = Rider(i, "pass $i", totdist.toDouble(), fareperpassenger.toInt())

            var a = riders.add(rider)

        }


        // rider = Rider(2,"pass2",20.0,200.0)

        // a = riders.add(rider)

        return riders


    }

}
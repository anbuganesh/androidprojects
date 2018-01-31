package com.example.ganesh.expenseshare

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_share_ride.*
import kotlinx.android.synthetic.main.rowshare_ride.view.*
import java.nio.Buffer


class ShareRide : AppCompatActivity(), TextWatcher {



    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

  /*  fun EditText.onChange(cb: (String) -> Unit) {
        this.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                recyclerview_population()

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }*/



    private lateinit var adapter: RecyclerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private var ridersList : ArrayList<Rider> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_ride)

        title=getString(R.string.titleshare)


        linearLayoutManager = LinearLayoutManager(this)


        RecyclerView.layoutManager = linearLayoutManager




          editTextPassngerCount.addTextChangedListener(this)


        val buttonshareride = findViewById<Button>(R.id.buttonComputeShare)


        buttonshareride.setOnClickListener(){
            var totkm :Double = 0.0


            for ( i in 0..adapter.itemCount-1) {

                val view = RecyclerView.getChildAt(i)

                val km = view.findViewById<EditText>(R.id.editTextkm)

                totkm = totkm + km.text.toString().toDouble()

                println("kilo meter $totkm ")

            }

            val totfare = findViewById<EditText>(R.id.editTextTotalFare)

            val perkmfare = totfare.text.toString().toDouble()/totkm

            var ridersarr : ArrayList<Rider> = ArrayList()



            for ( i in 0..adapter.itemCount-1) {

                val view = RecyclerView.getChildAt(i)

                val km = view.findViewById<EditText>(R.id.editTextkm)

                var textFare = view.findViewById<EditText>(R.id.editTextFare)


                        val kmtravelled :Double = km.text.toString().toDouble()


                val fare :Int =  (kmtravelled * perkmfare).toInt()

                var rider = Rider(i, "pass $i", kmtravelled, fare)

                var a = ridersarr.add(rider)

                println("fare $fare")

               // textFare.setText(fare)

            }
            adapter = RecyclerAdapter(ridersarr)
            RecyclerView.adapter = adapter




        }



    }
    fun recyclerview_population(){

        getRiderList()
        adapter = RecyclerAdapter(ridersList)
        RecyclerView.adapter = adapter

    }
    fun passengerCnt(): Int{

        var pcnt :EditText = findViewById<EditText>(R.id.editTextPassngerCount)

        var passengercnt :Int = Integer.parseInt(pcnt.text.toString() )

        return passengercnt

    }


    fun TotalFare(): Int{

        var totfare :EditText = findViewById<EditText>(R.id.editTextTotalFare)

        var totalfare :Int = Integer.parseInt(totfare.text.toString())

        return totalfare

    }


    fun TotalKm(): Int{

        var totkm :EditText = findViewById<EditText>(R.id.editTextTotalDistance)

        var totalkm :Int = Integer.parseInt(totkm.text.toString() )

        return totalkm

    }


    fun getRiderList()
    {
        var ridermodel = RiderModel()
        ridersList = ridermodel.getRidersdummy(TotalFare(),TotalKm(),passengerCnt())
       // ridersList = ridermodel.getRidersdummy(1000,10,8)


    }


    override fun afterTextChanged(p0: Editable?) {

        toast("after text change")
       recyclerview_population()

        val inputManager: InputMethodManager =getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.SHOW_FORCED)


    }
}

private fun EditText.textWatcher(function: () -> Unit) {}

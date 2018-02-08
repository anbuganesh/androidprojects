package com.example.ganesh.expenseshare

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_share_ride.*
//import sun.util.locale.provider.LocaleProviderAdapter.getAdapter


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
    private lateinit var gridLayoutManager: GridLayoutManager

    private var ridersList : ArrayList<Rider> = ArrayList()


    private val lastVisibleItemPosition: Int
        get() = if (RecyclerView.layoutManager == linearLayoutManager) {
            linearLayoutManager.findLastVisibleItemPosition()
        } else {
            gridLayoutManager.findLastVisibleItemPosition()
        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_ride)

        title=getString(R.string.titleshare)



        linearLayoutManager = LinearLayoutManager(this)


        RecyclerView.layoutManager = linearLayoutManager




          editTextPassngerCount.addTextChangedListener(this)

        val buttonresetride = findViewById<Button>(R.id.buttonReset)


        buttonresetride.setOnClickListener()
        {
            val totfare = findViewById<EditText>(R.id.editTextTotalDistance1)

            totfare.setText("")

            val totdist = findViewById<EditText>(R.id.editTextTotalFare)

            totdist.setText("")

            val passcnt = findViewById<EditText>(R.id.editTextPassngerCount)

            passcnt.setText("")

            totfare.requestFocus()




        }


        val buttonshareride = findViewById<Button>(R.id.buttonComputeShare)


        buttonshareride.setOnClickListener(){
            var totkm :Double = 0.0


            for ( i in 1..adapter.itemCount-1) {

                val view = RecyclerView.getChildAt(i)

                val km = view.findViewById<EditText>(R.id.editTextkm)

                totkm = totkm + km.text.toString().toDouble()

                println("kilo meter $totkm ")

            }

            val totfare = findViewById<EditText>(R.id.editTextTotalFare)

            val perkmfare = totfare.text.toString().toDouble()/totkm

            var ridersarr : ArrayList<Rider> = ArrayList()

            var rider = Rider(0, "header", 0.00 ,0)
            var a = ridersarr.add(rider)


            for ( i in 1..adapter.itemCount-1) {

                val view = RecyclerView.getChildAt(i)

                val km = view.findViewById<EditText>(R.id.editTextkm)

                var textFare = view.findViewById<EditText>(R.id.editTextFare)


                        val kmtravelled :Double = km.text.toString().toDouble()


                val fare :Int =  (kmtravelled * perkmfare).toInt()

                var rider = Rider(i+1, "pass $i", kmtravelled, fare)

                var a = ridersarr.add(rider)

                println("fare $fare")

               // textFare.setText(fare)

            }
            adapter = RecyclerAdapter(ridersarr)
            RecyclerView.adapter = adapter

            val inputManager: InputMethodManager =getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            //if (currentFocus.windowToken != null)
            //inputManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.SHOW_FORCED)


        }




           // if (Build.VERSION.SDK_INT >= 11) {
          /*      RecyclerView.addOnLayoutChangeListener(View.OnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
                  println("Inside layout change listener")
                    var currentScrollPosition = 0

                    if (bottom < oldBottom) {
                        RecyclerView.postDelayed(Runnable { RecyclerView.scrollToPosition(6) }, 100)
                    }

                    if (bottom < oldBottom) {
                        RecyclerView.postDelayed(Runnable {
                            RecyclerView.smoothScrollToPosition(
                                    RecyclerView.getAdapter().getItemCount() - 1)
                        }, 100)
                    }*/

                    /*if (bottom < oldBottom) {
                        if (currentScrollPosition >=
                                RecyclerView.computeVerticalScrollRange()) {
                            RecyclerView.post {
                                RecyclerView.overScrollMode = View.OVER_SCROLL_NEVER
                                RecyclerView.smoothScrollBy(0, RecyclerView.computeVerticalScrollRange() - RecyclerView.computeVerticalScrollOffset() + RecyclerView.computeVerticalScrollExtent())
                            }
                        }
                    } else {
                        RecyclerView.overScrollMode = View.OVER_SCROLL_ALWAYS
                    }
                })*/
                RecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    var currentScrollPosition = 0


                    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                        //println("On scrolled")
                        super.onScrolled(recyclerView, dx, dy)

                        currentScrollPosition = recyclerView!!.computeVerticalScrollOffset() + recyclerView?.computeVerticalScrollExtent()
                    }

                    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) { }
                })
            //}






    }

 /*   private fun setRecyclerViewScrollListener() {
        RecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView!!.layoutManager.itemCount
                //if (!imageRequester.isLoadingData && totalItemCount == lastVisibleItemPosition + 1) {
                //    requestPhoto()
                // }
            }
        })
    }*/

    fun passengerCnt(): Int{

        var pcnt :EditText = findViewById<EditText>(R.id.editTextPassngerCount)

        var passengercnt: Int =0

        if (pcnt.text.toString().isNotEmpty()) {
             passengercnt = Integer.parseInt(pcnt.text.toString())
        }


        return passengercnt

    }


    fun recyclerview_population(){

        val pcnt = passengerCnt()


       if (  pcnt > 0 ){
           getRiderList()
       }
        else {
           ridersList = ArrayList()
       }

        adapter = RecyclerAdapter(ridersList)
        println("before adapter assignment call")
        RecyclerView.adapter = adapter
        //setRecyclerViewScrollListener()

       }

    fun TotalFare(): Int{

        var totfare :EditText = findViewById<EditText>(R.id.editTextTotalFare)

        var totalfare :Int = Integer.parseInt(totfare.text.toString())

        return totalfare

    }


    fun TotalKm(): Int{
        var totkm :EditText = findViewById<EditText>(R.id.editTextTotalDistance1)
        var totalkm :Int = Integer.parseInt(totkm.text.toString() )
        return totalkm
    }


    fun getRiderList()
    {
        var ridermodel = RiderModel()
        ridersList = ridermodel.getRidersdummy(TotalFare(),TotalKm(),passengerCnt())
        println("after getRiderList")
       // ridersList = ridermodel.getRidersdummy(1000,10,8)
    }


    override fun afterTextChanged(p0: Editable?) {

        //toast("after text change")
       recyclerview_population()

        val inputManager: InputMethodManager =getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.SHOW_FORCED)
    }







}

//private fun EditText.textWatcher(function: () -> Unit) {}

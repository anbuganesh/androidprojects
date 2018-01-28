package com.example.ganesh.expenseshare

/**
 * Created by ganesh on 28-01-2018.
 */

import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rowshare_ride.view.*

class RecyclerAdapter(private val riders: ArrayList<Rider>) : RecyclerView.Adapter<RecyclerAdapter.RiderHolder>(){
    override fun getItemCount(): Int {
       return riders.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.RiderHolder, position: Int) {
        val itemRider = riders[position]
        holder.bindRider(itemRider)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerAdapter.RiderHolder {
        var inflatedView = parent!!.inflate(R.layout.rowshare_ride, false)
        return RiderHolder(inflatedView)    }


    class RiderHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        private var view: View = v
        private var rider: Rider? = null

        //3
        init {
            v.setOnClickListener(this)
        }

        //4
        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }

        companion object {
            //5
            private val RIDER_KEY = "RIDER"
        }

        fun bindRider(rider: Rider) {
            this.rider = rider
            //Picasso.with(view.context).load(photo.url).into(view.itemImage)

            var name = rider.name.toString()
            view.editTextName.setText(rider.name)

            view.editTextkm.setText(rider.rideKm.toString())

            view.editTextFare.setText(rider.rideAmount.toString())


           // view.itemDate.text = photo.humanDate
            //view.itemDescription.text = photo.explanation
        }
    }



}


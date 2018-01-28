package com.example.ganesh.expenseshare

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_share_ride.*





class ShareRide : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_ride)

        title=getString(R.string.titleshare)

        linearLayoutManager = LinearLayoutManager(this)


        RecyclerView.layoutManager = linearLayoutManager

        /*var realm = Realm.getDefaultInstance()

        var ridermodel = RiderModel()

        var results = ridermodel.getRiders(realm)

        val list = ArrayList(results)
        */
        var ridermodel = RiderModel()

        var list1 = ridermodel.getRidersdummy()

      //  val list = ArrayList(results)



        adapter = RecyclerAdapter(list1)
        RecyclerView.adapter = adapter

    }
}

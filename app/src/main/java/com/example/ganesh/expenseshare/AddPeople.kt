package com.example.ganesh.expenseshare

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.*
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add_people.*

class AddPeople : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_people)

        title = "Manage Members"
        var realm = Realm.getDefaultInstance()
        val query = realm.where(Member ::class.java)

        var results = query.findAll()
       // val button = findViewById<Button>(R.id.buttonAddMember)
        val membername = findViewById<EditText>(R.id.textaddMember)

        val listItem = findViewById<ListView>(R.id.ListViewMember)

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,results)

        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val hideSoftInputFromWindow = inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0)


        listItem.adapter = adapter


               buttonAddMember.setOnClickListener {

                   println("Hello")






                  val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                  val hideSoftInputFromWindow = inputMethodManager.hideSoftInputFromWindow(membername.windowToken, 0)
                   //imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                   //val query = realm.where(Member ::class.java)

                    //results = query.findAll()



                   var member = Member()

                   member.name = membername.text.toString()

                   realm.beginTransaction()

                   realm.copyToRealm(member)

                   realm.commitTransaction()



                   val query = realm.where(Member ::class.java)

                   val results = query.findAll()

                   for (item in results){
                       println(item.name)


                   }

                   val listItem = findViewById<ListView>(R.id.ListViewMember)

                   val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,results)

                   listItem.adapter = adapter



               }
    }
}

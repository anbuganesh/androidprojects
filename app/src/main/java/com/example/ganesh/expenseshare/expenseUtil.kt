package com.example.ganesh.expenseshare

import android.app.Activity
import android.content.Context
import android.widget.Toast

/**
 * Created by ganesh on 16-01-2018.
 */

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}


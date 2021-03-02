package com.example.coroutines.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutines.R
import kotlinx.android.synthetic.main.activity_count_game.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountGameActivity() : AppCompatActivity() {

    private val MAX_COUNT = 30
    private val START_COUNT = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_game)

        start_button.setOnClickListener {
            count()
        }
    }

    private fun count() {
        GlobalScope.launch () {
            withContext(Main){
                for (i in START_COUNT..MAX_COUNT) {
                    delay(500)
                    Log.i("count", i.toString())
                    count_text.text = i.toString()
                }
            }

        }
    }
}


package com.example.coroutines

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_count_game.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.coroutines.CoroutineContext

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


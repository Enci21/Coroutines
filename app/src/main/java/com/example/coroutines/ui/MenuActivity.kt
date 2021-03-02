package com.example.coroutines.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutines.R
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        count_game_button.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, CountGameActivity::class.java))
        })

        progress_game_button.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, ProgressActivity::class.java))
        })

        room_button.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, RoomActivity::class.java))
        })
    }
}
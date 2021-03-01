package com.example.coroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
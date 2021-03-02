package com.example.coroutines.ui

import adapters.RecyclerAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines.R
import database.entity.Word
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import util.toast
import viewModels.RoomViewModel

class RoomActivity : AppCompatActivity() {

    private lateinit var viewModel: RoomViewModel
    private lateinit var adapter: RecyclerAdapter
    private lateinit var words: List<Word>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        //getAllWords()
        CoroutineScope(IO).launch { getAllWords() }

        viewModel = ViewModelProvider(this).get(RoomViewModel::class.java)

        recycler.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter(words)
        recycler.adapter = adapter

    }

    fun save(view: View) {
        if (editText.text.isNullOrEmpty()) {
            viewModel.insertWord(Word(editText.text.toString()), this)
            toast("Saved!", this)
        } else {
            toast("Something went wrong, please try again later!", this)
        }
    }

    private suspend fun getAllWords() {
        withContext(CoroutineScope(IO).coroutineContext) {
            words = viewModel.getAllWords(this@RoomActivity)
        }
    }
}
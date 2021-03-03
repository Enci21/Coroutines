package com.example.coroutines.ui

import adapters.RecyclerAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines.R
import database.entity.Word
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import util.toast
import viewModels.RoomViewModel

class RoomActivity : AppCompatActivity() {

    private lateinit var viewModel: RoomViewModel
    private lateinit var adapter: RecyclerAdapter
    private var words: MutableList<Word> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        setUpRecycler()

        viewModel = RoomViewModel(this)  //ViewModelProvider(this).get(RoomViewModel::class.java)
        viewModel.getAllWords()?.observe(this, Observer {
            words = it
            adapter.setWordsList(words)
        })

        delete_button.setOnClickListener(View.OnClickListener { delete() })
    }

    fun save(view: View) {
        if (!editText.text.isNullOrEmpty()) {
            viewModel.insertWord(Word(editText.text.toString(), false), this)
            toast("Saved!", this)
        } else {
            toast("Something went wrong, please try again later!", this)
        }
    }

    private fun delete() {
        var iter = words.iterator()
        while (iter.hasNext()) {
            var w: Word = iter.next()
            if (w.isSelected) {
                // iter.remove()
                CoroutineScope(IO).launch {
                    viewModel.deleteWord(w)
                }
                adapter.notifyDataSetChanged()
            }
        }

    }

    private fun setUpRecycler() {
        recycler.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter(words)
        recycler.adapter = adapter
    }
}
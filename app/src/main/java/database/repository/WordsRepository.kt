package database.repository

import android.content.Context
import androidx.lifecycle.LiveData
import database.WordsDatabase
import database.entity.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class WordsRepository(context: Context) {

    private var words: LiveData<List<Word>>

    companion object {
        private var db: WordsDatabase? = null

        fun initDB(context: Context): WordsDatabase {
            return WordsDatabase.getDatabaseInstance(context)
        }
    }

    init {
        db = initDB(context)
        words = db?.dao()?.getAllWords()!!
    }

    fun insertWord(word: Word, context: Context) {
        if (db == null) {
            db = initDB(context)
        }

        CoroutineScope(IO).launch {
            db?.dao()?.insert(word)
        }
    }

    fun getAllWords(): LiveData<List<Word>>? {
        return words
    }

    fun deleteWord(word: Word) {
        db?.dao()?.deleteWord(word)
    }
}
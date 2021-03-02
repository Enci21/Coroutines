package database.repository

import android.content.Context
import database.WordsDatabase
import database.entity.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WordsRepository() {

    companion object {
        private var db: WordsDatabase? = null

        fun initDB(context: Context): WordsDatabase {
            return WordsDatabase.getDatabaseInstance(context)
        }
    }

    fun insertWord(word: Word, context: Context) {
        if (db == null) {
            db = initDB(context)
        }

        CoroutineScope(IO).launch {
            db?.dao()?.insert(word)
        }
    }

    suspend fun getAllWords(context: Context): List<Word> {
        var words: List<Word>?

        withContext(CoroutineScope(Unconfined).coroutineContext) {
            if (db == null) {
                db = initDB(context)
            }
            words = db?.dao()?.getAllWords()
        }
        return words!!
    }
}
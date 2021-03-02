package viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import database.entity.Word
import database.repository.WordsRepository

class RoomViewModel() : ViewModel() {

    private val repo: WordsRepository = WordsRepository()

    fun insertWord(word: Word, context: Context) {
        repo.insertWord(word, context)
    }

    suspend fun getAllWords(context: Context): List<Word> {
        return repo.getAllWords(context)
    }
}
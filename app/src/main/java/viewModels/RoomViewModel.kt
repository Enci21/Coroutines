package viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import database.entity.Word
import database.repository.WordsRepository


class RoomViewModel(context: Context) : ViewModel() {

    private val repo: WordsRepository = WordsRepository(context)
    private var words: LiveData<MutableList<Word>>? = repo.getAllWords()

    init {
        repo.getAllWords()
    }

    fun insertWord(word: Word, context: Context) {
        repo.insertWord(word, context)
    }

    fun getAllWords(): LiveData<MutableList<Word>>? {
        return words
    }

    fun deleteWord(word: Word) {
        repo.deleteWord(word)
    }
}
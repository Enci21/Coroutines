package database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import database.entity.Word

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word)

    @Query("SELECT * FROM words")
    fun getAllWords(): LiveData<List<Word>>

    @Delete
    fun deleteWord(word: Word)
}
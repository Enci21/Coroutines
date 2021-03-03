package database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import database.entity.Word

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word)

    @Query("SELECT * FROM words")
    fun getAllWords(): LiveData<List<Word>>
}
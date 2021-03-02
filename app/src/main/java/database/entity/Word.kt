package database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = false)
    val word: String
)
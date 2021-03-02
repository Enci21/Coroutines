package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import database.dao.WordDao
import database.entity.Word

@Database(entities = arrayOf(Word::class), version = 2, exportSchema = false)
abstract class WordsDatabase : RoomDatabase() {

    abstract fun dao(): WordDao

    companion object {

        @Volatile
        private var databaseInstance: WordsDatabase? = null

        fun getDatabaseInstance(context: Context): WordsDatabase {
            if (databaseInstance != null) {
                return databaseInstance!!
            }
            synchronized(this) {
                databaseInstance = Room
                    .databaseBuilder(context, WordsDatabase::class.java, "WordsDatabase")
                    .fallbackToDestructiveMigration()
                    .build()
                return databaseInstance!!
            }
        }
    }


}
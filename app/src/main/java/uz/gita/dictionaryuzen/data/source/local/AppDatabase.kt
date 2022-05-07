package uz.gita.dictionaryuzen.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.dictionaryuzen.data.source.local.dao.TranslationDao
import uz.gita.dictionaryuzen.data.source.local.dao.WordDao
import uz.gita.dictionaryuzen.data.source.local.entity.TranslationEntity
import uz.gita.dictionaryuzen.data.source.local.entity.WordEntity

@Database(entities = [WordEntity::class, TranslationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getWordDao(): WordDao
    abstract fun getTranslationDao(): TranslationDao

    companion object {
        private var INSTANSE: AppDatabase? = null

        fun init(context: Context): AppDatabase {
            val temp = INSTANSE

            if (temp != null) {
                return temp
            }
            val instanse = Room.databaseBuilder(context, AppDatabase::class.java, "uzen.db")
                .createFromAsset("uzen.db")
                .allowMainThreadQueries()
                .build()

            INSTANSE = instanse
            return instanse
        }
    }
}
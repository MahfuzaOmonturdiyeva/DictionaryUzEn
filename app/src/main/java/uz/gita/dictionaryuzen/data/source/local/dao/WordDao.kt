package uz.gita.dictionaryuzen.data.source.local.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Query
import uz.gita.dictionaryuzen.data.model.common.WordData

@Dao
interface WordDao {

    @Query("SELECT*FROM word")
    fun getAllWords(): Cursor

    @Query("SELECT * FROM word WHERE isFav=1")
    fun getWordsFavorites(): Cursor

    @Query("SELECT*FROM word WHERE name like:query")
    fun getSearchableWords(query: String): Cursor

    @Query("SELECT*FROM word WHERE isFav=1 and name like:query")
    fun getFavoriteSearchableWords(query: String): Cursor

    @Query("SELECT*FROM word where _id=:id")
    fun getTranslationWords(id: Int): WordData

    @Query("UPDATE word Set isFav=:isFav WHERE _id=:id")
    fun updateWord(isFav: Int, id: Int)

}
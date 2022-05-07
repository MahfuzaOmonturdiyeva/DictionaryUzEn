package uz.gita.dictionaryuzen.domain.repository

import android.database.Cursor
import androidx.lifecycle.LiveData
import uz.gita.dictionaryuzen.data.model.common.WordData
import uz.gita.dictionaryuzen.data.model.common.WordDataWithCategory

interface Repository {

    /**
     *load all words uz-en from room
     */
    fun getAllWords(): Cursor

    /**
     * load all favorite words uz-en from room
     */
    fun getFavorites(): Cursor

    /**
     * get searchable words from room
     */
    fun getSearchableWords(query: String): Cursor

    /**
     * get searchable and favorite words from room
     */
    fun getFavoriteSearchableWords(query: String): Cursor

    /**
     * set favorite
     */
    fun updateWord(isFav: Int, id: Int)

    /**
     * get all translations of the word from room
     */
    fun getTranslationWords(id:Int): LiveData<List<WordDataWithCategory>>
}
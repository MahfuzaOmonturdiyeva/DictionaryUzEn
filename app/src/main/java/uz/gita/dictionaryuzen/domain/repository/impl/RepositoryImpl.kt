package uz.gita.dictionaryuzen.domain.repository.impl

import android.database.Cursor
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.gita.dictionaryuzen.data.model.common.WordData
import uz.gita.dictionaryuzen.data.model.common.WordDataWithCategory
import uz.gita.dictionaryuzen.data.source.local.dao.TranslationDao
import uz.gita.dictionaryuzen.data.source.local.dao.WordDao
import uz.gita.dictionaryuzen.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val wordDao: WordDao,
    private val translationDao: TranslationDao
) : Repository {
    override fun getAllWords(): Cursor = wordDao.getAllWords()

    override fun getFavorites(): Cursor = wordDao.getWordsFavorites()

    override fun getSearchableWords(query: String): Cursor = wordDao.getSearchableWords(query)

    override fun getFavoriteSearchableWords(query: String): Cursor =
        wordDao.getFavoriteSearchableWords(query)

    override fun updateWord(isFav: Int, id: Int) = wordDao.updateWord(isFav, id)

    override fun getTranslationWords(id: Int): List<WordDataWithCategory> {
        val listTranslationWordsId = translationDao.getTranslationId(id)
        val listTranslationWords = ArrayList<WordDataWithCategory>()
        val word = wordDao.getTranslationWords(id)
        listTranslationWords.add(
            WordDataWithCategory(
                word._id,
                word.name,
                word.langId,
                word.transcription,
                word.isFav,
                word.Note,
                0
            )
        )
        for (item in listTranslationWordsId) {

            val word = wordDao.getTranslationWords(item.idTranslation)
            val wordWithCategory = WordDataWithCategory(
                word._id,
                word.name,
                word.langId,
                word.transcription,
                word.isFav,
                word.Note,
                if (item.idCategory == null) 0 else item.idCategory!!
            )
            listTranslationWords.add(wordWithCategory)
        }
        val mutableLiveData = MutableLiveData<List<WordDataWithCategory>>()
        mutableLiveData.value = listTranslationWords
        return listTranslationWords
    }
}
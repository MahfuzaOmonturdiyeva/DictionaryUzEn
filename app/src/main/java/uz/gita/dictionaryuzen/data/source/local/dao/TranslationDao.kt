package uz.gita.dictionaryuzen.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import uz.gita.dictionaryuzen.data.model.common.TranslationData

@Dao
interface TranslationDao {

    @Query("SELECT*FROM translation WHERE idWord=:idWord ")
    fun getTranslationId(idWord: Int): List<TranslationData>

}
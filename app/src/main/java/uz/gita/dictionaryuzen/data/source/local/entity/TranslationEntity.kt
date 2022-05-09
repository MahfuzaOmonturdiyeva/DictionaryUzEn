package uz.gita.dictionaryuzen.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "translation")
data class TranslationEntity(
    @PrimaryKey
    val _id: Int,
    val idWord: Int,
    val idTranslation: Int,
    val idCategory: Int
)
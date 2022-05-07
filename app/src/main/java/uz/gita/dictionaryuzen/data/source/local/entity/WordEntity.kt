package uz.gita.dictionaryuzen.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "word")
data class WordEntity(
    @PrimaryKey
    val _id: Int?,
    val name: String?,
    val langId: Int?,
    val transcription: Char?,
    val isFav: Int?=0,
    val Note: String?
)
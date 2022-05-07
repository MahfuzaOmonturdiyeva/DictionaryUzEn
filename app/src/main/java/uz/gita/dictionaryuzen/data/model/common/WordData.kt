package uz.gita.dictionaryuzen.data.model.common

data class WordData(
    val _id: Int?,
    val name: String?,
    val langId: Int?,
    val transcription: Char?,
    val isFav: Int?,
    val Note: String?
)
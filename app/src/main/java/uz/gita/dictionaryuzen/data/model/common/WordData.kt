package uz.gita.dictionaryuzen.data.model.common

data class WordData(
    val _id: Int,
    val name: String,
    val langId: Int,
    val transcription: String?,
    val isFav: Int=0,
    val Note: String=""
)
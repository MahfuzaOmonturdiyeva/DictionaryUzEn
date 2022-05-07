package uz.gita.dictionaryuzen.data.model.common

data class WordDataWithCategory(
    val _id: Int?,
    val name: String?,
    val langId: Int?,
    val transcription: Char?,
    val isFav: Int?,
    val Note: String?,
    val idCategory:Int?
)
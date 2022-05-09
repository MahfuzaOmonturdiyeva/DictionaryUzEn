package uz.gita.dictionaryuzen.data.model.common

data class WordDataWithCategory(
    val _id: Int,
    val name: String,
    val langId: Int,
    val transcription: String?,
    var isFav: Int=0,
    val Note: String="",
    val idCategory:Int
)
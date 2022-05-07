package uz.gita.dictionaryuzen.presenter

import android.database.Cursor
import androidx.lifecycle.LiveData
import uz.gita.dictionaryuzen.data.model.common.WordDataWithCategory

interface FavoriteViewModel {
    val openDialogLiveData: LiveData<List<WordDataWithCategory>>
    val joinFavoriteWordLiveData: LiveData<Cursor>
    val onShareWordLiveData:LiveData<String>
    val onCopyWordLiveData:LiveData<String>
    val outputVoiceWordLiveData:LiveData<String>
    val setSpeechTextSearchViewLiveData:LiveData<Unit>

    fun joinFavoriteWords()
    fun searchFavoriteWord(query:String)
    fun updateWord(isFav:Int, id:Int)
    fun getTranslationWords(id:Int)
    fun onShare(string:String)
    fun onCopy(string:String)
    fun onOutputVoice(string:String)
    fun onSpeech()
}
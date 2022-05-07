package uz.gita.dictionaryuzen.presenter

import android.database.Cursor
import androidx.lifecycle.LiveData
import uz.gita.dictionaryuzen.data.model.common.WordData
import uz.gita.dictionaryuzen.data.model.common.WordDataWithCategory

interface SearchViewModel {

    val openDialogLiveData: LiveData<List<WordDataWithCategory>>
    val joinWordLiveData:LiveData<Cursor>
    val onShareWordLiveData:LiveData<String>
    val onCopyWordLiveData:LiveData<String>
    val outputVoiceWordLiveData:LiveData<String>
    val setSpeechTextSearchViewLiveData:LiveData<Unit>

    fun joinWords()
    fun search(query:String)
    fun updateWord(isFav:Int, id:Int)
    fun getTranslationWords(id:Int)
    fun onShare(string:String)
    fun onCopy(string:String)
    fun onOutputVoice(string:String)
    fun onSpeech()
}
package uz.gita.dictionaryuzen.presenter.impl

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.dictionaryuzen.data.model.common.WordData
import uz.gita.dictionaryuzen.data.model.common.WordDataWithCategory
import uz.gita.dictionaryuzen.domain.repository.Repository
import uz.gita.dictionaryuzen.presenter.SearchViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModelImpl @Inject constructor(
    private val repository: Repository
) : SearchViewModel, ViewModel() {
    override var openDialogLiveData: LiveData<List<WordDataWithCategory>> = MutableLiveData()
    override val joinWordLiveData: MutableLiveData<Cursor> = MutableLiveData()
    override val onShareWordLiveData: MutableLiveData<String> = MutableLiveData()
    override val onCopyWordLiveData: MutableLiveData<String> = MutableLiveData()
    override val outputVoiceWordLiveData: MutableLiveData<String> = MutableLiveData()
    override val setSpeechTextSearchViewLiveData: MutableLiveData<Unit> =MutableLiveData()

    override fun joinWords() {
        joinWordLiveData.value = repository.getAllWords()
    }

    override fun search(query: String) {
        joinWordLiveData.value = repository.getSearchableWords("%$query%")
    }

    override fun updateWord(isFav: Int, id: Int) {
        repository.updateWord(isFav, id)
    }

    override fun getTranslationWords(id: Int) {
        openDialogLiveData = repository.getTranslationWords(id)
    }

    override fun onShare(string: String) {
        onShareWordLiveData.value = string
    }

    override fun onCopy(string: String) {
        onCopyWordLiveData.value = string
    }

    override fun onOutputVoice(string: String) {
        outputVoiceWordLiveData.value = string
    }
    override fun onSpeech() {
        setSpeechTextSearchViewLiveData.value=Unit
    }
}
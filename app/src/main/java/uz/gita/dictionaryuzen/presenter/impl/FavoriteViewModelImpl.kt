package uz.gita.dictionaryuzen.presenter.impl

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.dictionaryuzen.data.model.common.WordData
import uz.gita.dictionaryuzen.data.model.common.WordDataWithCategory
import uz.gita.dictionaryuzen.domain.repository.Repository
import uz.gita.dictionaryuzen.presenter.FavoriteViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModelImpl @Inject constructor(
    private val repository: Repository
) : FavoriteViewModel, ViewModel() {
    override var openDialogLiveData: LiveData<List<WordDataWithCategory>> = MutableLiveData()
    override val joinFavoriteWordLiveData: MutableLiveData<Cursor> = MutableLiveData()
    override val onShareWordLiveData: MutableLiveData<String> = MutableLiveData()
    override val onCopyWordLiveData: MutableLiveData<String> = MutableLiveData()
    override val outputVoiceWordLiveData: MutableLiveData<String> = MutableLiveData()
    override val setSpeechTextSearchViewLiveData: MutableLiveData<Unit> =MutableLiveData()

    override fun joinFavoriteWords() {
        joinFavoriteWordLiveData.value = repository.getFavorites()
    }

    override fun searchFavoriteWord(query: String) {
        joinFavoriteWordLiveData.value = repository.getFavoriteSearchableWords("%$query%")
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
        onCopyWordLiveData.value=string
    }

    override fun onOutputVoice(string: String) {
        outputVoiceWordLiveData.value=string
    }

    override fun onSpeech() {
        setSpeechTextSearchViewLiveData.value=Unit
    }
}
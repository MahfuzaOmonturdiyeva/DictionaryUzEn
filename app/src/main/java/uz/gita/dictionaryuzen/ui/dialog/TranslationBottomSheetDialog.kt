package uz.gita.dictionaryuzen.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.gita.dictionaryuzen.R
import uz.gita.dictionaryuzen.data.model.common.UpdateData
import uz.gita.dictionaryuzen.data.model.common.WordDataWithCategory
import uz.gita.dictionaryuzen.databinding.DialogTranslateBottomSheetBinding
import uz.gita.dictionaryuzen.ui.adapter.TranslationAdapter

class TranslationBottomSheetDialog(
    context: Context,
) : BottomSheetDialog(context) {
    private val adapter = TranslationAdapter()
    private var onclickFavoriteListener: ((UpdateData) -> Unit)? = null
    private var onclickShareListener: ((String) -> Unit)? = null
    private var onclickCopyListener: ((String) -> Unit)? = null
    private var onclickVoiceListener: ((String) -> Unit)? = null

    fun setOnClickFavoriteListener(l: (UpdateData) -> Unit) {
        onclickFavoriteListener = l
    }

    fun setOnClickShareListener(l: (String) -> Unit) {
        onclickShareListener = l
    }

    fun setOnClickCopyListener(l: (String) -> Unit) {
        onclickShareListener = l
    }

    fun setOnClickVoiceListener(l: (String) -> Unit) {
        onclickShareListener = l
    }

    private val binding = DialogTranslateBottomSheetBinding.inflate(LayoutInflater.from(context))
    private var dialog = BottomSheetDialog(context)

    init {
        dialog.setContentView(binding.root)
        dialog.show()
    }

    fun setContact(list: List<WordDataWithCategory>) {
        val list1 = list as ArrayList
        val word = list1[0]
        list1.removeAt(0)
        var string = "${word.name} - "
        for (item in list1)
            string += item.name

        binding.apply {
            this.tvWord.text = word.name
            this.tvTranscription.text = word.transcription.toString()
            this.imgBtnFavorite.setBackgroundResource(
                if (word.isFav == 0) {
                    R.drawable.ic_star
                } else R.drawable.ic_star_bold
            )
            this.rvContainerChapterWithLessons.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.rvContainerChapterWithLessons.adapter = adapter
            adapter.submitList(list)
            this.imgBtnFavorite.setOnClickListener {
                val data = UpdateData(
                    if (word.isFav == 0) {
                        1
                    } else 0, word._id!!
                )
                onclickFavoriteListener?.let { it1 ->
                    it1(
                        data
                    )
                }
            }
            this.imgBtnShare.setOnClickListener {
                onclickShareListener?.let { it1 -> it1(string) }
            }
            this.imgBtnCopy.setOnClickListener {
                onclickCopyListener?.let { it1 -> it1(string) }
            }
            this.imgBtnVoice.setOnClickListener {
                onclickVoiceListener?.let { it1 -> it1(word.name!!) }
            }
        }
    }
}

package uz.gita.dictionaryuzen.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.dictionaryuzen.data.model.common.WordDataWithCategory
import uz.gita.dictionaryuzen.databinding.ItemTranslateBinding

class TranslationAdapter : ListAdapter<WordDataWithCategory, TranslationAdapter.Holder>(object :
    DiffUtil.ItemCallback<WordDataWithCategory>() {
    override fun areItemsTheSame(
        oldItem: WordDataWithCategory,
        newItem: WordDataWithCategory
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: WordDataWithCategory,
        newItem: WordDataWithCategory
    ): Boolean {
        return oldItem == newItem
    }
}) {
    private var onclickVoiceListener: ((String) -> Unit)? = null

    fun setOnClickVoiceListener(l: (String) -> Unit) {
        onclickVoiceListener = l
    }

    inner class Holder(private val itemTranslateBinding: ItemTranslateBinding) :
        RecyclerView.ViewHolder(itemTranslateBinding.root) {
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            if (item.langId == 1) {
                itemTranslateBinding.imgBtnVoice.visibility = View.VISIBLE
                itemTranslateBinding.imgBtnVoice.setOnClickListener {
                    onclickVoiceListener?.let { it1 -> it1(item.name!!) }
                }
            } else {
                itemTranslateBinding.imgBtnVoice.visibility = View.GONE
            }
            itemTranslateBinding.tvWord.text = item.name
            when (item.idCategory) {
                1 -> itemTranslateBinding.tvCategoryWord.text = "adv"
                2 -> itemTranslateBinding.tvCategoryWord.text = "pre"
                3 -> itemTranslateBinding.tvCategoryWord.text = "c"
                4 -> itemTranslateBinding.tvCategoryWord.text = ""
                5 -> itemTranslateBinding.tvCategoryWord.text = "pro"
                6 -> itemTranslateBinding.tvCategoryWord.text = "adj"
                7 -> itemTranslateBinding.tvCategoryWord.text = "v"
                8 -> itemTranslateBinding.tvCategoryWord.text = "n"

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        ItemTranslateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()
}
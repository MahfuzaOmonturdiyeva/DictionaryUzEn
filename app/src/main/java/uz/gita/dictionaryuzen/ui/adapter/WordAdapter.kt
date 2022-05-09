package uz.gita.dictionaryuzen.ui.adapter

import android.database.Cursor
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.dictionaryuzen.R
import uz.gita.dictionaryuzen.data.model.common.UpdateData
import uz.gita.dictionaryuzen.databinding.ItemWordBinding
import uz.gita.dictionaryuzen.extensions.toColoredSpannable

class WordAdapter : RecyclerView.Adapter<WordAdapter.Holder>() {
    private var cursor: Cursor? = null
    private var query: String? = null
    private var onclickFavoriteListener: ((UpdateData) -> Unit)? = null
    private var onclickWordListener: ((Int) -> Unit)? = null

    fun setOnClickFavoriteListener(l: (UpdateData) -> Unit) {
        onclickFavoriteListener = l
    }

    fun setOnClickWordListener(l: (Int) -> Unit) {
        onclickWordListener = l
    }

    fun submitCursor(cursor: Cursor, query: String? = null) {
        this.cursor = cursor
        this.query = query
        notifyDataSetChanged()
    }

    inner class Holder(private val itemWordBinding: ItemWordBinding) :
        RecyclerView.ViewHolder(itemWordBinding.root) {
        fun bind() {
            cursor?.let {
                it.moveToPosition(adapterPosition)
                val id = it.getInt(it.getColumnIndexOrThrow("_id"))

                val isFav = it.getInt(it.getColumnIndexOrThrow("isFav"))

                itemWordBinding.line.setOnClickListener {
                    onclickWordListener?.let { it1 ->
                        it1(id)
                    }
                }

                itemWordBinding.tvWord.text = it.getString(it.getColumnIndexOrThrow("name"))
                    .toColoredSpannable(query.toString())

                itemWordBinding.imgBtnFavorite.setImageResource(
                    if (isFav == 0) {
                        R.drawable.ic_star
                    } else {
                        R.drawable.ic_star_bold
                    }
                )
                itemWordBinding.imgBtnFavorite.setOnClickListener {
                    val data = UpdateData(
                        if (isFav == 0) {
                            1
                        } else {
                            0
                        }, id
                    )
                    onclickFavoriteListener?.let { it1 ->
                        it1(data)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()

    override fun getItemCount(): Int {
        cursor?.let {
            return it.count
        }
        return 0
    }
}
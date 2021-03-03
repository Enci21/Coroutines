package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines.R
import database.entity.Word
import kotlinx.android.synthetic.main.word_obj.view.*

class RecyclerAdapter(words: List<Word>) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    var words: List<Word> = words

    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var text: TextView = itemView.text_word
    }

    fun setWordsList(list: List<Word>) {
        words = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_obj, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = words.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        var currentWord: Word = words[position]
        holder.text.text = currentWord.word
        if (currentWord.isSelected) {
            holder.text.setBackgroundColor(holder.itemView.context.resources.getColor(R.color.blue))
        } else {
            holder.text.setBackgroundColor(holder.itemView.context.resources.getColor(R.color.black))
        }
        holder.text.setOnClickListener(View.OnClickListener {
            currentWord.isSelected = !currentWord.isSelected
            notifyItemChanged(holder.adapterPosition)
        })
    }
}
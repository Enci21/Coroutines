package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines.R
import database.entity.Word
import kotlinx.android.synthetic.main.word_obj.view.*

class RecyclerAdapter(words: List<Word>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var words: List<Word> = words

    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var text: TextView = itemView.findViewById(R.id.textView)

        init {
            text = view.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_obj, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var currentWord: Word = words[position]
        holder.itemView.text_word.text = currentWord.word
    }

    override fun getItemCount(): Int {
        return words.size
    }
}
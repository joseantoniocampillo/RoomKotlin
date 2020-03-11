package es.jacampillo.roomkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words  = emptyList<Word>() //Cacheada copia de words

    inner class WordViewHolder (itemview: View) : RecyclerView.ViewHolder(itemview){
        val wordItemView: TextView = itemview.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemview = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current  = words[position]
        holder.wordItemView.text = current.word
    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size
}
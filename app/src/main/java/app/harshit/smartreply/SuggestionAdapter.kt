package app.harshit.smartreply

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_suggestion.view.*

class SuggestionAdapter(private val suggestions: List<String>) : RecyclerView.Adapter<SuggestionAdapter.SuggestionHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): SuggestionHolder {
        return SuggestionHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.item_suggestion,
                viewGroup,
                false
            )
        )
    }

    override fun getItemCount() = suggestions.size

    override fun onBindViewHolder(holder: SuggestionHolder, position: Int) {
        holder.itemView.tvSuggestion.text = suggestions[position]
    }

    inner class SuggestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
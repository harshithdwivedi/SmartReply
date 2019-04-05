package app.harshit.smartreply

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.ml.naturallanguage.smartreply.FirebaseTextMessage
import kotlinx.android.synthetic.main.item_messgae_received.view.*
import kotlinx.android.synthetic.main.item_messgae_sent.view.*

class MessageAdapter(private val messages: List<FirebaseTextMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, itemType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(container.context)
        return SentHolder(inflater.inflate(R.layout.item_messgae_sent, container, false))
    }

    override fun getItemCount() = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tvSent.text = messages[position].zzmy()
    }

    inner class SentHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
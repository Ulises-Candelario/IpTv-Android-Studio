package com.hanzeel.iptvandroidstudio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DefaultChannelAdapter(
    private val channelList: List<Channel>,
    private val onChannelSelect: (Channel) -> Unit
) : RecyclerView.Adapter<DefaultChannelAdapter.ChannelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.default_channel_item, parent, false)
        return ChannelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        val channel = channelList[position]
        holder.bind(channel)

        // Manejar clic en el botón "Agregar Canal"
        holder.buttonAddChannel.setOnClickListener {
            onChannelSelect(channel)
        }
    }

    override fun getItemCount(): Int = channelList.size

    inner class ChannelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        private val textViewCategory: TextView = itemView.findViewById(R.id.textViewCategory)
        val buttonAddChannel: Button = itemView.findViewById(R.id.buttonAddChannel)

        fun bind(channel: Channel) {
            textViewName.text = channel.name
            textViewCategory.text = channel.category
            Glide.with(itemView.context).load(channel.image).into(imageView)
        }
    }
}

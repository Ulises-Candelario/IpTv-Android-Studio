package com.hanzeel.iptvandroidstudio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapter(
    private val channelList: MutableList<Channel>,
    private val onChannelClick: (Channel) -> Unit,
    private val onChangeChannelClick: (Channel) -> Unit,
    private val onDeleteChannelClick: (Channel) -> Unit  // Añadimos el parámetro para eliminar
) : RecyclerView.Adapter<RecyclerAdapter.ChannelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.channel_item, parent, false)
        return ChannelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        val channel = channelList[position]
        holder.bind(channel)

        // Manejar el clic en el ítem del canal
        holder.itemView.setOnClickListener { onChannelClick(channel) }

        // Manejar el clic en el botón "Cambiar Canal"
        holder.changeChannelButton.setOnClickListener {
            onChangeChannelClick(channel)
        }

        // Manejar el clic en el botón "Eliminar Canal"
        holder.deleteChannelButton.setOnClickListener {
            onDeleteChannelClick(channel)
        }
    }

    override fun getItemCount(): Int = channelList.size

    inner class ChannelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        private val textViewCategory: TextView = itemView.findViewById(R.id.textViewCategory)
        val changeChannelButton: Button = itemView.findViewById(R.id.change_channel_button)
        val deleteChannelButton: Button = itemView.findViewById(R.id.delete_channel_button) // Añadimos el botón de eliminar

        fun bind(channel: Channel) {
            textViewName.text = channel.name
            textViewCategory.text = channel.category
            Glide.with(itemView.context)
                .load(channel.image)
                .into(imageView)
        }
    }
}

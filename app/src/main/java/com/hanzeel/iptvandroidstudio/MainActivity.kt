package com.hanzeel.iptvandroidstudio

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var channelAdapter: RecyclerAdapter
    private var channelList: MutableList<Channel> = mutableListOf()
    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar SharedPreferences para guardar los canales
        sharedPreferences = getSharedPreferences("channels_pref", Context.MODE_PRIVATE)

        // Inicializar RecyclerView y el adaptador
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Botón flotante para agregar un nuevo canal
        val fabAddChannel = findViewById<FloatingActionButton>(R.id.fab_add_channel)
        fabAddChannel.setOnClickListener {
            openAddChannelActivity()  // Método para agregar canal
        }

        // Cargar los canales guardados o predeterminados
        loadChannels()
    }

    // Método para abrir la actividad de agregar canal
    private fun openAddChannelActivity() {
        val newChannel = Channel(
            id = channelList.size + 1,
            name = "",
            category = "",
            link = "",
            image = ""
        )
        val intent = Intent(this, EditChannelActivity::class.java)
        intent.putExtra("channel", newChannel)
        startActivityForResult(intent, ADD_CHANNEL_REQUEST_CODE)
    }

    // Método para abrir la actividad del reproductor de video
    private fun openVideoPlayerActivity(channelLink: String) {
        val intent = Intent(this, VideoPlayerActivity::class.java)
        intent.putExtra("channel_link", channelLink)

        // Pasar la lista de canales como JSON
        val channelsJson = gson.toJson(channelList)
        intent.putExtra("channel_list", channelsJson)

        startActivity(intent)
    }

    // Método para cargar los canales desde SharedPreferences o el repositorio
    private fun loadChannels() {
        val channelsJson = sharedPreferences.getString("channels_list", null)
        if (channelsJson != null) {
            val type = object : TypeToken<MutableList<Channel>>() {}.type
            channelList = gson.fromJson(channelsJson, type)
        } else {
            // Cargar los canales predeterminados desde el repositorio
            val channelRepository = ChannelRepository()
            channelList = channelRepository.getDefaultChannels()

            // Guardar los canales predeterminados en SharedPreferences
            saveChannelsToPreferences()
        }

        // Inicializar el adaptador con la lista de canales
        channelAdapter = RecyclerAdapter(channelList, { selectedChannel ->
            openEditChannelActivity(selectedChannel) // Abre la actividad para editar el canal
        }, { channelToChange ->
            openVideoPlayerActivity(channelToChange.link) // Abre el reproductor solo con el botón
        }, { channelToDelete ->
            deleteChannel(channelToDelete) // Lógica para eliminar el canal
        })

        recyclerView.adapter = channelAdapter
    }

    // Método para guardar los canales en SharedPreferences
    private fun saveChannelsToPreferences() {
        val channelsJson = gson.toJson(channelList)
        sharedPreferences.edit().putString("channels_list", channelsJson).apply()
    }

    // Método para abrir la actividad de editar canal
    private fun openEditChannelActivity(channel: Channel) {
        val intent = Intent(this, EditChannelActivity::class.java)
        intent.putExtra("channel", channel)
        startActivityForResult(intent, EDIT_CHANNEL_REQUEST_CODE)
    }

    // Método para eliminar un canal
    private fun deleteChannel(channel: Channel) {
        channelList.remove(channel)
        channelAdapter.notifyDataSetChanged()
        saveChannelsToPreferences() // Guardar cambios
    }

    // Manejar el resultado de agregar o editar un canal
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val updatedChannel = data.getParcelableExtra<Channel>("updatedChannel")
            updatedChannel?.let {
                if (requestCode == ADD_CHANNEL_REQUEST_CODE) {
                    channelList.add(it)
                } else if (requestCode == EDIT_CHANNEL_REQUEST_CODE) {
                    val index = channelList.indexOfFirst { channel -> channel.id == it.id }
                    if (index != -1) {
                        channelList[index] = it
                    }
                }
                channelAdapter.notifyDataSetChanged()
                saveChannelsToPreferences() // Guardar los cambios
            }
        }
    }

    companion object {
        const val ADD_CHANNEL_REQUEST_CODE = 1
        const val EDIT_CHANNEL_REQUEST_CODE = 2
    }
}

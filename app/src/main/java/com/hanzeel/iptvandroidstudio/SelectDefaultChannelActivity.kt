package com.hanzeel.iptvandroidstudio

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SelectDefaultChannelActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var channelAdapter: DefaultChannelAdapter
    private var channelList: MutableList<Channel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_default_channel)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Cargar los canales predeterminados
        val channelRepository = ChannelRepository()
        channelList = channelRepository.getDefaultChannels()

        // Verificar si la lista se está cargando correctamente
        Log.d("SelectDefaultChannel", "Cantidad de canales predeterminados: ${channelList.size}")

        // Configurar el adaptador
        channelAdapter = DefaultChannelAdapter(channelList) { selectedChannel ->
            // Cuando se seleccione un canal, volver con el resultado
            val resultIntent = Intent()
            resultIntent.putExtra("selectedChannel", selectedChannel)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        recyclerView.adapter = channelAdapter
    }
}

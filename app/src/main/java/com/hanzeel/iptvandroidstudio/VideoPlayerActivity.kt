package com.hanzeel.iptvandroidstudio

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer
import org.videolan.libvlc.util.VLCVideoLayout
import android.net.Uri
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var videoLayout: VLCVideoLayout
    private lateinit var libVLC: LibVLC
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var fabMenu: View
    private var channelList: MutableList<Channel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        // Inicializar VLC para Android (LibVLC)
        videoLayout = findViewById(R.id.videoLayout)
        libVLC = LibVLC(this)
        mediaPlayer = MediaPlayer(libVLC)
        mediaPlayer.attachViews(videoLayout, null, false, false)

        // Cargar el canal seleccionado y reproducirlo
        val channelLink = intent.getStringExtra("channel_link") ?: ""
        if (channelLink.isNotEmpty()) {
            changeChannel(channelLink)
        } else {
            Log.e("VideoPlayerActivity", "No se recibió ningún enlace de canal")
        }

        // Obtener la lista de canales desde el Intent
        val channelsJson = intent.getStringExtra("channel_list")
        if (channelsJson != null) {
            val type = object : TypeToken<MutableList<Channel>>() {}.type
            channelList = Gson().fromJson(channelsJson, type)
        }

        // Inicializar el botón flotante y el menú
        fabMenu = findViewById(R.id.fab_menu)
        fabMenu.setOnClickListener {
            showPopupMenu(it)
        }
    }

    // Método para mostrar el menú emergente
    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.video_player_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_return_to_list -> {
                    finish()
                    true
                }
                R.id.action_change_channel -> {
                    showChannelSelectionMenu()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    // Mostrar lista de canales como submenú
    private fun showChannelSelectionMenu() {
        val popupMenu = PopupMenu(this, fabMenu)

        // Usar la lista de canales recibida desde MainActivity
        channelList.forEach { channel ->
            popupMenu.menu.add(channel.name)
        }

        popupMenu.setOnMenuItemClickListener { item ->
            val selectedChannel = channelList.find { it.name == item.title }
            selectedChannel?.let {
                changeChannel(it.link)
            } ?: Log.e("VideoPlayerActivity", "No se encontró el canal seleccionado")
            true
        }
        popupMenu.show()
    }

    // Método para cambiar el canal
    private fun changeChannel(link: String) {
        if (link.isNotEmpty()) {
            mediaPlayer.stop()
            val media = Media(libVLC, Uri.parse(link))
            mediaPlayer.media = media
            mediaPlayer.play()
            Log.d("VideoPlayerActivity", "Cambiando al canal: $link")
        } else {
            Log.e("VideoPlayerActivity", "El enlace del canal está vacío")
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
        mediaPlayer.detachViews()
        mediaPlayer.release()
    }
}

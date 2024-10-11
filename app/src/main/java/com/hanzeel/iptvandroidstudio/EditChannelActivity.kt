package com.hanzeel.iptvandroidstudio

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.contract.ActivityResultContracts

class EditChannelActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextCategory: EditText
    private lateinit var editTextLink: EditText
    private lateinit var editTextImage: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonDelete: Button
    private lateinit var buttonSelectDefaultChannel: Button
    private lateinit var channel: Channel

    // Lanzador para seleccionar un canal predeterminado
    private val selectDefaultChannelLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedChannel: Channel? = result.data?.getParcelableExtra("selectedChannel")
            selectedChannel?.let {
                fillChannelForm(it) // Rellenar los campos del formulario
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_channel)

        channel = intent.getParcelableExtra("channel")!!

        editTextName = findViewById(R.id.editTextName)
        editTextCategory = findViewById(R.id.editTextCategory)
        editTextLink = findViewById(R.id.editTextLink)
        editTextImage = findViewById(R.id.editTextImage)
        buttonSave = findViewById(R.id.buttonSave)
        buttonDelete = findViewById(R.id.buttonDelete)
        buttonSelectDefaultChannel = findViewById(R.id.buttonSelectDefaultChannel)

        // Rellenar campos si el canal existe
        editTextName.setText(channel.name)
        editTextCategory.setText(channel.category)
        editTextLink.setText(channel.link)
        editTextImage.setText(channel.image)

        buttonSave.setOnClickListener { saveChannel() }

        buttonDelete.setOnClickListener { deleteChannel() }

        buttonSelectDefaultChannel.setOnClickListener {
            // Abrir la actividad para seleccionar un canal predeterminado
            val intent = Intent(this, SelectDefaultChannelActivity::class.java)
            selectDefaultChannelLauncher.launch(intent)
        }
    }

    private fun fillChannelForm(selectedChannel: Channel) {
        editTextName.setText(selectedChannel.name)
        editTextCategory.setText(selectedChannel.category)
        editTextLink.setText(selectedChannel.link)
        editTextImage.setText(selectedChannel.image)
    }

    private fun saveChannel() {
        val updatedChannel = Channel(
            id = channel.id,
            name = editTextName.text.toString(),
            category = editTextCategory.text.toString(),
            link = editTextLink.text.toString(),
            image = editTextImage.text.toString()
        )

        val resultIntent = Intent()
        resultIntent.putExtra("updatedChannel", updatedChannel)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    private fun deleteChannel() {
        val resultIntent = Intent()
        resultIntent.putExtra("deletedChannel", channel)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}

package com.example.emailselfie

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.example.emailselfie.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var tmpUri: Uri? = null
    private val takeImageLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it) {
                binding.imageView.setImageURI(tmpUri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.takePictureButton.setOnClickListener {
            takeImage()
        }

        binding.sendButton.setOnClickListener {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_SUBJECT, "КПП НАИ-196 Тарасенко")
            i.putExtra(Intent.EXTRA_STREAM, tmpUri)
            startActivity(i)
        }
    }

    private fun takeImage() {
        lifecycleScope.launchWhenStarted {
            initUri().let {
                tmpUri = it
                takeImageLauncher.launch(it)
            }
        }
    }

    private fun initUri(): Uri {
        val tmpFile = File.createTempFile("tmp_image_file", ".png", cacheDir).apply {
            createNewFile()
            deleteOnExit()
        }

        return FileProvider.getUriForFile(
            applicationContext,
            getString(R.string.authorities),
            tmpFile
        )
    }
}
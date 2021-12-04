package com.example.lab05_2

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val imageURI = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/Van_Gogh_-_Starry_Night_-_Google_Art_Project.jpg/757px-Van_Gogh_-_Starry_Night_-_Google_Art_Project.jpg"
    private val imageDownloader = ImageDownloader()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<View>(R.id.imageView) as ImageView

        imageDownloader.downloadImage(imageURI) // Скачиваем картинку
        imageDownloader.getBitmapData().observe(this) { // Выводим картинку на экран
            imageView.setImageBitmap(it)
        }
    }
}
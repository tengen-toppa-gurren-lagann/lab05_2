package com.example.lab05_2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ImageDownloader: ViewModel() {
    private val bitmapData = MutableLiveData<Bitmap>()
    private lateinit var executor: ExecutorService
    fun getBitmapData() : LiveData<Bitmap> = bitmapData // Наружу отдаем неизменяемую LiveData - так безопаснее

    fun downloadImage(uri:String) { // Загрузка картинки по заданной ссылке - в отдельном потоке
        executor = Executors.newSingleThreadExecutor() // Нужен только один поток
        executor.execute {
            val imageURL = URL(uri)
            val bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream())
            bitmapData.postValue(bitmap)
        }
    }

    override fun onCleared() { // Вызывается перед уничтожением ViewModel - используем для остановки потока
        executor.shutdown()
        super.onCleared()
    }
}
package com.example.internetrequest.a_one

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.internetrequest.URL
import com.example.internetrequest.databinding.ActivityMainBinding
import java.net.URL

/**
 * Creator: ED
 * Date: 2021/11/17 16:37
 * Mail: salahayo3192@gmail.com
 */
internal class OneActivityForKotlinRefactor : AppCompatActivity() {

    private val mHandler = Handler(Looper.getMainLooper())

    private val viewBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater, null, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)


        viewBinding.btnFetchData.setOnClickListener { fetchRemoteData() }
    }

    private fun fetchRemoteData() {
        Thread {
            val data = getData(URL)
            mHandler.post { viewBinding.tvResult.text = data }
        }.start()
    }

    private fun getData(url: String): String? {
        try {
            return URL(url).readText()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}
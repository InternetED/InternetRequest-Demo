package com.example.internetrequest.a_one

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Looper
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import com.example.internetrequest.R
import com.example.internetrequest.URL
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

/**
 * Creator: ED
 * Date: 2021/11/17 16:37
 * Mail: salahayo3192@gmail.com
 */
internal class OneActivityForKotlin : AppCompatActivity() {


    var btnFetchData: Button? = null
    var tvResult: TextView? = null
    private val mHandler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnFetchData = findViewById(R.id.btnFetchData)
        tvResult = findViewById(R.id.tvResult)

        btnFetchData!!.setOnClickListener(View.OnClickListener { fetchRemoteData() })
    }

    private fun fetchRemoteData() {
        Thread {
            val data = getData(URL)
            mHandler.post { tvResult!!.text = data }
        }.start()
    }

    private fun getData(url: String): String? {

        var httpURLConnection: HttpURLConnection? = null
        try {
            httpURLConnection = URL(url).openConnection() as HttpURLConnection
            httpURLConnection.connect()
            val stringBuilder = StringBuilder()
            val bufferedReader = BufferedReader(
                InputStreamReader(
                    httpURLConnection!!.inputStream
                )
            )
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append("\n")
            }
            bufferedReader.close()
            return stringBuilder.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            httpURLConnection?.disconnect()
        }
        return null
    }
}
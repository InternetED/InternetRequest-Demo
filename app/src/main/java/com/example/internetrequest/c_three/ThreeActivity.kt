package com.example.internetrequest.c_three

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.internetrequest.URL
import com.example.internetrequest.databinding.ActivityMainBinding
import java.net.URL

/**
 * Creator: ED
 * Date: 2021/11/17 17:30
 * Mail: salahayo3192@gmail.com
 *
 * **/
class ThreeActivity : AppCompatActivity() {

    private val viewBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater, null, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)


        viewBinding.btnFetchData.setOnClickListener {
            GetDataTask {
                viewBinding.tvResult.setText(it)
            }.execute(URL)
        }
    }


    class GetDataTask(private val callback: (String?) -> Unit) : AsyncTask<String, Int, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            // 執行前，驗證數據
        }

        override fun doInBackground(vararg params: String?): String {
            if (params == null)
                return ""

            val url = params[0]

            return URL(url).readText()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            callback(result)
        }
    }

}
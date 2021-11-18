package com.example.internetrequest.e_five

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.internetrequest.URL
import com.example.internetrequest.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

/**
 * Creator: ED
 * Date: 2021/11/18 10:12
 * Mail: salahayo3192@gmail.com
 *
 * **/
class FiveActivityRefactor : AppCompatActivity() {


    private val viewBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater, null, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)


        viewBinding.btnFetchData.setOnClickListener {


            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val readText = URL(URL).readText()
                    withContext(Dispatchers.Main) {
                        viewBinding.tvResult.text = readText
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
package com.example.internetrequest.b_two

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.example.internetrequest.URL
import com.example.internetrequest.databinding.ActivityMainBinding

/**
 * Creator: ED
 * Date: 2021/11/17 16:47
 * Mail: salahayo3192@gmail.com
 *
 * **/
class TwoActivity : AppCompatActivity(), Handler.Callback {


    private val threadHandler = ThreadHandler(this)

    private val viewBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater, null, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        // start thread && pending task input
        threadHandler.start()

        viewBinding.btnFetchData.setOnClickListener {
            threadHandler.submit(Task(URL))
        }
    }

    override fun handleMessage(msg: Message): Boolean {
        when (msg.what) {
            Task.TASK_FETCH_DATA -> {
                viewBinding.tvResult.setText(msg.data.toString())
                return true
            }
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        threadHandler.close()
    }
}
package com.example.internetrequest.d_four

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.internetrequest.URL
import com.example.internetrequest.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.URL

/**
 * Creator: ED
 * Date: 2021/11/17 17:40
 * Mail: salahayo3192@gmail.com
 *
 * **/
class FourActivity : AppCompatActivity() {

    private val viewBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater, null, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)


        viewBinding.btnFetchData.setOnClickListener {
            getData(URL)
        }
    }

    private fun getData(url: String) {

        Single.create<String> {
            try {
                val readText = URL(url).readText()
                it.onSuccess(readText)
            } catch (e: Exception) {
                it.onError(e)
            }

        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                viewBinding.tvResult.setText(it)
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()

    }

}
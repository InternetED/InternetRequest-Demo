package com.example.internetrequest.b_two

import android.os.Handler
import android.os.Looper
import java.net.URL
import java.util.concurrent.LinkedBlockingQueue


/**
 * Creator: ED
 * Date: 2021/11/17 17:00
 * Mail: salahayo3192@gmail.com
 *
 * **/
class ThreadHandler(callback: Handler.Callback) : Thread() {

    private var isRunning = false

    private val taskQueue = LinkedBlockingQueue<Task>()

    private val handler = Handler(Looper.getMainLooper(),callback)

    override fun run() {
        super.run()
        // 組塞
        while (isRunning) {
            val task = taskQueue.poll()
            task?.setCallback(handler)
            task?.run()
        }
    }

    fun submit(task: Task) {
        taskQueue.add(task)
    }


    override fun start() {
        isRunning = true
        super.start()
    }


    fun close() {
        isRunning = false
    }
}

class Task(private val url: String) : Runnable {
    companion object {
        const val TASK_FETCH_DATA = 1
        const val HANDLER_DATA = "handler_data"
    }

    private var mHandler: Handler? = null
    override fun run() {
        val readText = URL(url).readText()


        mHandler?.also {handler->
            with(handler.obtainMessage(TASK_FETCH_DATA)){
                data.putString(HANDLER_DATA,readText)
                handler.sendMessage(this)
            }
        }
    }

    fun setCallback(handler: Handler) {
        mHandler = handler
    }

}
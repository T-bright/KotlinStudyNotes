package tsw.kotlin.newcharacteristics.a_kotlingrammar.g_coroutines

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_coroutines.*
import kotlinx.coroutines.*
import tsw.kotlin.newcharacteristics.R

class CoroutinesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
    }

    //lanuch和withContext的使用
    fun oneTest(view: View) {
        MainScope().launch {
            Log.e("A", "0 ThreadName : ${Thread.currentThread().name}")
            var ss = System.currentTimeMillis()
            var one = withContext(Dispatchers.IO) {
                Log.e("A", "1 ThreadName : ${Thread.currentThread().name}")
                Thread.sleep(1000)
                "1"
            }
            Log.e("A", "1 Time : ${System.currentTimeMillis() - ss}")
            Log.e("A", "2 ThreadName : ${Thread.currentThread().name}")
            var two = withContext(Dispatchers.IO) {
                Log.e("A", "3 ThreadName : ${Thread.currentThread().name}")
                Thread.sleep(2000)
                "2"
            }
            Log.e("A", "4 ThreadName : ${Thread.currentThread().name}")
            Log.e("A", "2 Time : ${System.currentTimeMillis() - ss}")
            Log.e("A", "Result :  ${one}+${two}")
            Log.e("A", "3 Time : ${System.currentTimeMillis() - ss}")
        }
    }

    //lanuch和async的使用
    fun twoTest(view: View) {
        MainScope().launch {
            Log.e("A", "0 ThreadName : ${Thread.currentThread().name}")
            var ss = System.currentTimeMillis()
            var one = async(Dispatchers.IO) {
                Log.e("A", "1 ThreadName : ${Thread.currentThread().name}")
                Thread.sleep(1000)
                "1"
            }
            Log.e("A", "1 Time : ${System.currentTimeMillis() - ss}")
            Log.e("A", "2 ThreadName : ${Thread.currentThread().name}")
            var two = async (Dispatchers.IO) {
                Log.e("A", "3 ThreadName : ${Thread.currentThread().name}")
                Thread.sleep(2000)
                "2"
            }
            Log.e("A", "4 ThreadName : ${Thread.currentThread().name}")
            Log.e("A", "2 Time : ${System.currentTimeMillis() - ss}")
            Log.e("A", "Result :  ${one.await()}+${two.await()}")
            Log.e("A", "3 Time : ${System.currentTimeMillis() - ss}")
        }
    }

    //申明挂起函数
    fun thirdTest(view: View) {
        var job = MainScope().launch {
            Log.e("A", "0 ThreadName : ${Thread.currentThread().name}")
            var ss = System.currentTimeMillis()
            var one = async { getOne() }
            Log.e("A", "1 Time : ${System.currentTimeMillis() - ss}")
            Log.e("A", "2 ThreadName : ${Thread.currentThread().name}")
            var two = async { getTwo() }
            Log.e("A", "4 ThreadName : ${Thread.currentThread().name}")
            Log.e("A", "2 Time : ${System.currentTimeMillis() - ss}")
            Log.e("A", "Result :  ${one.await()}+${two.await()}")
            Log.e("A", "3 Time : ${System.currentTimeMillis() - ss}")
        }
        job.cancel()
    }

    private suspend fun getOne(): String {
        return withContext(Dispatchers.IO) {
            Log.e("A", "1 ThreadName : ${Thread.currentThread().name}")
            Thread.sleep(1000)
            "1"
        }
    }

    private suspend fun getTwo(): String {
        return withContext(Dispatchers.IO) {
            Log.e("A", "3 ThreadName : ${Thread.currentThread().name}")
            Thread.sleep(2000)
            "2"
        }
    }

    //runBlocking和lanuch、async的区别
    fun fourTest(view: View) {
        Log.e("A", "1 ThreadName : ${Thread.currentThread().name}")
        runBlocking {
            Thread.sleep(100)
            Log.e("A", "2 ThreadName : ${Thread.currentThread().name}")
        }
        MainScope().launch {
            Thread.sleep(1000)
            Log.e("A", "3 ThreadName : ${Thread.currentThread().name}")
        }
        MainScope().async {
            Thread.sleep(1000)
            Log.e("A", "4 ThreadName : ${Thread.currentThread().name}")
        }
        Log.e("A", "5 ThreadName : ${Thread.currentThread().name}")
    }
}

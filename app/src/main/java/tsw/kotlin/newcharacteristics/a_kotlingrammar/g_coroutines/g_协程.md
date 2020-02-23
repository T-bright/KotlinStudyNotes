# 一、协程大致简介
### 1、协程是什么
讲的通俗点，就是一套处理线程的一套api。
### 2、协程的优势
可以用类似同步的方式写出异步代码，也就是在同一个代码块里可以进行多次的线程切换，没有各种回调嵌套的烦恼，代码更加的简洁明了。
# 二、协程的使用
### 1、协程的三种开启方式

- GlobalScope.launch
- GlobalScope.async
- runBlocking
    ```
    GlobalScope.launch{
    
    }
    GlobalScope.async{
    
    }
    runBlocking{
    
    }
    ```

### 2、具体的使用
具体的使用：主要就是关于线程切换方面的。比如说网络请求、本地的一些耗时IO操作等。android一般都是先通过 ``` MainScope().launch ``` 开启一个协程，然后通过
```  withContext(Dispatchers.IO) ``` （Dispatchers有几种模式）做到线程切换的目的。

一般来说一个协程里面,多个 ``` withContext(Dispatchers.IO) ``` 之间的执行并不是并行的。如：
```
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
```
执行的结果:时间是3秒多。说明他们之间不是并行的。
```
0 ThreadName : main
1 ThreadName : DefaultDispatcher-worker-3
1 Time : 1016
2 ThreadName : main
3 ThreadName : DefaultDispatcher-worker-1
4 ThreadName : main
2 Time : 3021
Result :  1+2
3 Time : 3021
```

</br>
</br>
</br>


这其实不是很友好，尤其是当一个页面，有多个接口需要请求时。这时我们可以通过``` async ```来实现。示例如下：
```
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
```
执行的结果如下:时间是2秒多。说明他们之间是并行的。
```
0 ThreadName : main
1 Time : 15
2 ThreadName : main
1 ThreadName : DefaultDispatcher-worker-1
4 ThreadName : main
2 Time : 17
3 ThreadName : DefaultDispatcher-worker-3
Result :  1+2
 Time : 2023
```

</br>
</br>
</br>

另外就是挂起函数的申明，上述代码可以改造成如下形式：
```
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
```
上述示例中函数 getOne 和 getTwo 就是挂起函数。挂起函数必须要有suspend关键字。但是并不是有了suspend关键字，就可以了，挂起函数真正起作用的是里面的实际代码 withContext。

一般来说，在有耗时操作的时候，我们会申明一个挂起函数。比如本地IO操作等，就可以申明成挂起函数。


</br>
</br>
</br>

最后就是runBlocking，这玩意它会阻塞当前的主线程。但是``` .launch 和 .async ``` 不会。看示例：
```
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
```
执行结果
```
1 ThreadName : main
2 ThreadName : main
5 ThreadName : main
3 ThreadName : main
4 ThreadName : main
```
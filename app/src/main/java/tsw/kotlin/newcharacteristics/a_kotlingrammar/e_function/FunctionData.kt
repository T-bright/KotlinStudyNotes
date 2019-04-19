package tsw.kotlin.newcharacteristics.a_kotlingrammar.e_function

import android.content.Context
import android.util.Log
import tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects.DataClass
import tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects.DataClassTwo
import java.io.BufferedReader
import java.io.FileReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import kotlin.concurrent.thread

class FunctionData {
    val TAG = "FunctionData"
    //-------------------------------------------基本的---------------------------------------------------------
    //无输入无输出参数。无输出也就是该方法没有返回值，Java语言用void表示，Kotlin语言用Unit表示，代码如下
    //Unit可以省略，但是如果是其他的返回值类型，则不可以省略
    public fun noInputAndNoOutPut_Parameters(): Unit {

    }

    //无输入有输出参数。Unit可以省略，但是这里的String不可以省略
    public fun noInputAndHaveOutPut_Parameters(): String {
        return "无输入有输出参数"
    }

    //有输入有输出参数,此时的输入参数不可以为null，否则编译器不会通过
    public fun hvaeInputAndHaveOutPut_Parameters(input: String): String {
        return "有输入有输出参数：${input}"
    }

    //输入的参数可以为空,可以这么写
    public fun parameterCanBeNull(intut: String?): String {

        return "有输入有输出参数 ： ${intut}"
    }

    //默认参数
    fun defaultParameter(one: String = "默认参数one", two: String = "默认参数two", three: String = "默认参数three"): String {
        return "拼接后的字符串 ： ${one}; ${two} ; ${three} "
    }

    //可变参数
    fun changeParameter(vararg args: String): String {
        var stringBuilder = StringBuilder()
        for (item in args) {
            stringBuilder.append(item)
            stringBuilder.append(" ")
        }
        return stringBuilder.toString()
    }

    //可变参数
    fun changeParameter2(vararg args: Array<String>): String {
        var stringBuilder = StringBuilder()
        for (items in args) {
            for (item in items) {
                stringBuilder.append(item)
                stringBuilder.append(" ")
            }
            stringBuilder.append("\n")
        }
        return stringBuilder.toString()
    }

    //如果一个函数只有一个一行切有return关键字，那么我们用 = 来代替{}
    fun sum(x: Int, y: Int) = x + y


    //--------------------------------高阶函数---------------------------------------------------------------------------

    fun functionFun(one: String, two: String): String {
        return "${one} ${two}"
    }

    //形参是函数，返回值也是函数
    fun functionFunc(f1: String, f2: String, method: (one: String, two: String) -> String): String {
        return method(f1, f2)
    }

    //let函数
    fun functionLet() {
        var arr = arrayOf("a", "b", "c", null)
        var type = arr.forEach {
            it.let {
                Log.e(TAG, it.toString())
            }
        }

        var dataClass: DataClass? = DataClass("张三", "男", 12)
        var size = dataClass?.let {
            Log.e(TAG, "name = ${it.name};sex = ${it.sex};age = ${it.age}")
        }
        Log.e(TAG, size.toString())
    }

    //with函数
    fun functionWith() {
        var dataClass = DataClass("张三", "男", 12)
        var name = with(dataClass) {
            Log.e(TAG, "name = ${this.name};sex = ${sex};age = ${age}")
            name
        }
        Log.e(TAG, name.toString())
    }

    //fun函数
    fun functionRun() {
        var arr = arrayOf("a", "b", "c", null)
        var type = arr.forEach {
            it?.run {
                Log.e(TAG, it.toString())
            }
        }

        var dataClass: DataClass? = DataClass("张三", null, 12)
        var size = dataClass?.run {
            Log.e(TAG, "name = ${name};sex = ${sex};age = ${age}")
            sex
        }
        Log.e(TAG, size.toString())
    }

    //apply函数
    fun functionApply() {
        var dataClass: DataClass? = DataClass("张三", null, 12)
        var applyObject = dataClass?.apply {
            sex = "男"
        }
        Log.e(TAG, applyObject.toString())

        //多层级非空判定
        var dataClassTwo = DataClassTwo(1, null)
        dataClassTwo?.apply {
            Log.e(TAG, "dataClassTwo不为空")
        }.dataClass?.apply {
            Log.e(TAG, "dataClass不为空")
        }
    }

    //also函数
    fun functionAlso() {
        var dataClass: DataClass? = DataClass("张三", null, 12)
        var alsoObject = dataClass.also {
            it?.sex = "男"
            100//此时返回的不是 100，而是dataClass对象
        }
        Log.e(TAG, alsoObject.toString())
    }

    //filter函数
    fun functionFilter() {
        var originalArr = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        var destinationArr = mutableListOf<Int>(1, 2, 3)

        originalArr.filter { it > 0 }.forEach { Log.e(TAG, "filter : ${it.toString()}") }

        //可以是if...else
        originalArr.filter {
            if (it > 0) {
                return@filter true
            } else {
                return@filter false
            }
        }.forEach { Log.e(TAG, "filter : ${it.toString()}") }


        arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filterNotTo(mutableListOf<Int>(1, 2, 3)) { it > 0 }
            .forEach { Log.e(TAG, "filterNotTo : ${it.toString()}") }
        arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filterNot { boo(it) }
            .forEach { Log.e(TAG, "filterNot : ${it.toString()}") }

        arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filterIndexed { index, i -> index % 2 == 0 }
            .forEach { Log.e(TAG, "filterIndexed : ${it.toString()}") }
        arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filterIndexedTo(
            mutableListOf<Int>(
                1,
                2,
                3
            )
        ) { index, i -> index % 2 == 0 }.forEach { Log.e(TAG, "filterIndexedTo : ${it.toString()}") }
        arrayOf("a", "b", "c", null).filterNotNull().forEach { Log.e(TAG, "filterNotNull : ${it.toString()}") }
        arrayOf("a", "b", "c", null).filterNotNullTo(mutableListOf("a", null))
            .forEach { Log.e(TAG, "filterNotNullTo : ${it.toString()}") }
        arrayOf("a", "b", "c", 1, 2, 3).filterIsInstance(String::class.java)
            .forEach { Log.e(TAG, "filterIsInstance : ${it.toString()}") }
        arrayOf("a", "b", "c", 1, 2, 3).filterIsInstanceTo(mutableListOf("c", "d", 1, 2, 3, 4), String::class.java)
            .forEach { Log.e(TAG, "filterIsInstanceTo : ${it.toString()}") }

    }


    private fun boo(it: Int): Boolean {
        return it > 0
    }


    fun functionTakeWhile() {
        (1..10).takeWhile { it < 5 }.forEach { Log.e(TAG, "TakeWhile : ${it.toString()}") }

        arrayOf(1, 10, 2, 3, 4, 5, 6, 7, 8, 9).takeWhile { it < 5 }
            .forEach { Log.e(TAG, "TakeWhile : ${it.toString()}") }
    }


    fun functionMap() {
        (1..5).map { it * 2 }.forEach { Log.e(TAG, "map : ${it.toString()}") }//将数组里所有的值都乘以2
        Log.e(TAG, "\n\n")

        var arr1 = arrayOf(1, 2, 3)
        var arr2 = arrayOf(4, 5, 6)
        var arr3 = arrayListOf(arr1, arr2)
        arr3.forEach { Log.e(TAG, "arr3 forEach : ${it.toString()}") }
        Log.e(TAG, "\n\n")
        arr3.flatMap { iterator -> iterator.asList() }.forEach { Log.e(TAG, "flatMap : ${it.toString()}") }

    }


    fun functionReduce() {
        (1..5).map { (1..it).reduce { acc, i -> acc * i } }
            .forEach { Log.e(TAG, "reduce : ${it.toString()}") }//通过map转换，把集合中的每一个数转换成相应的阶层数
        (1..5).map { (1..it).reduce { acc, i -> i * i } }.forEach {
            Log.e(
                TAG,
                "reduce : ${it.toString()}"
            )
        }//通过map转换，把集合中的每一个数转换成相应的平方数。但是显然没必要写的这么麻烦，参照map，直接it*it就可以实现

    }

    fun functionFold() {
        var foldResStr = arrayOf("11", 22, "33").fold(StringBuffer()) { sb, i -> sb.append(i).append(",") }
        Log.e(TAG, "Fold : ${foldResStr.toString()}")//打印结果：11,22,33
    }

    fun functionUse(context: Context) {
        thread {
            var inputStream = context.assets.open("test.txt")
            var text = BufferedReader(InputStreamReader(inputStream, "UTF-8")).use {
                var sb = StringBuilder()
                var readLine: String?
                while (true) {
                    readLine = it.readLine() ?: break
                    sb.append(readLine)
                }
                sb.toString()
            }
            Log.e(TAG, "Fold : ${text}")
        }
    }


}
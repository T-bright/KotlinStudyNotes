package tsw.kotlin.newcharacteristics.a_kotlingrammar.e_function

import android.util.Log
import tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects.DataClass
import tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects.DataClassTwo
import java.lang.StringBuilder

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

        (0..6).filter { it % 2 == 0 }.forEach{Log.e(TAG, it.toString())}

        //可以是if...else
        (0..6).filter { if(it > 0){return@filter true} else return@filter false }.forEach{Log.e(TAG, it.toString())}
    }

    //filterIndexed、TakeWhile、Map、Flatmap、Fold、Reduce

}
package tsw.kotlin.newcharacteristics.a_kotlingrammar.e_function

import java.lang.StringBuilder

class FunctionData {

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
    public fun defaultParameter(one: String = "默认参数one", two: String = "默认参数two", three: String = "默认参数three"): String {
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





}
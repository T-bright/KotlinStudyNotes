package tsw.kotlin.newcharacteristics.a_kotlingrammar.c_cyclecontrol

/**
 * 循环控制
 */
class C_CycleControl {
    val array = arrayOf("aa", "bb", "cc")
    val list = listOf<String>("dd", "ee", "ff")

    //for循环控制
    fun forCycleControl() {
        println("=========================for循环函数======================")
        //for循环函数，遍历数组或者集合,后面只能放一个
        for (arr in array) println("for循环函数 ：${arr}")

        println()
        println("=========================for循环代码块遍历数组和集合======================")
        //for循环代码块,遍历数组或者集合
        for (arr in array) {
            println("for循环代码块 ：${arr}")
        }

        println()
        println("=========================for循环代码块，通过索引遍历数组和集合======================")
        for (index in array.indices) {
            println("for循环代码块，通过索引遍历 ：${array[index]}")
        }

        println()
        println("=========================for循环代码块，通过索引遍历数组和集合======================")
        for (index in array.indices) {
            println("for循环代码块，通过索引遍历 ：${array[index]}")
        }


        println()
        println("=========================for循环代码块，直接拿到索引和数组中的元素======================")
        for ((index, value) in array.withIndex()) {
            println("for循环代码块，直接拿到索引和数组中的元素 ，角标：${index} ；元素：${value}")
        }


        println()
        println("=========================迭代器遍历数组和集合======================")
//        var it = list.iterator()
        var it = list.iterator()
        for (item in it.iterator()) {
            println("for循环代码块，通过索引遍历 ：${item}")
        }

        println()
        println("=========================forEach遍历数组和集合(可以是数组、集合、迭代器)======================")
//        array.forEach {
//        list.forEach {
        it.forEach {
            println("forEach遍历 ：${it}")
        }

    }

    //循环中的返回和跳转
    fun forReturn() {
        var index = 0
        for (i in 1..10) {
            if (i == 5) {
                break//结束整个循环
            }
            index++
        }
        println("break ：${index}")

        index = 0
        for (i in 1..10) {
            if (i == 5) {
                continue//结束当前这一次循环
            }
            index++
        }
        println("continue ：${index}")

        index = 0
        for (i in 1..10) {
            if (i == 5) {
                return//结束当前forReturn方法
            }
            index = i
        }
        println("return ：${index}")
    }

    //while循环控制
    fun whileCycleControl() {
        var index = 0
        while (index < 10) {
            index++
        }
        println("index 第一次循环：${index}")
        do {
            index++
        } while (index < 10)
        println("index 第二次循环：${index}")
    }

}
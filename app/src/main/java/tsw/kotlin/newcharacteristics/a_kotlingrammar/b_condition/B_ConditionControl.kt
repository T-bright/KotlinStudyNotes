package tsw.kotlin.newcharacteristics.a_kotlingrammar.b_condition

/**
 * 条件表达式
 */
class B_ConditionControl {
    var a = 12;
    var b = 18;


    //if..else条件语句
    fun ifElseConditionControl() {
        //传统表达式
        var max = 0;
        if (a > b) {
            max = a
        } else {
            max = b
        }
        println("传统表达式 获取最大值 ：${max}")

        //作为表达式
        a = 22
        max = if (a > b) a else b
        println("作为表达式 获取最大值 ：${max}")


        //if判断是不是在一个区间内
        if (max in 1..30) {
            println("max 在区间内")
        } else {
            println("max 不在区间内")
        }

    }

    //when条件语句
    fun whenConditionControl() {
        // when 类似其他语言的 switch 操作符。
        // else 同 switch 的 default,如果其他分支都不满足条件将会求值 else 分支。
        // 如果很多分支需要用相同的方式处理，则可以把多个分支条件放在一起，用逗号分隔。
        // 检测一个值在（in）或者不在（!in）一个区间或者集合中。
        // 检测一个值是（is）或者不是（!is）一个特定类型的值。
        var intArrs = arrayOf(1, 2, 3, 4)
        var intArr = 3
        when (intArr) {
            1 -> println("intArr != 3")
            3 -> println("intArr == 3")
            3, 4 -> println("intArr = 3 or intArr = 4")
            in 1..4 -> println("intArr 在区间1-4中")          // 检测一个值在（in）或者不在（!in）一个区间或者集合中。
            in intArrs -> println("intArr 在数组intArrs中")   // 检测一个值在（in）或者不在（!in）一个区间或者集合中。
            !in 1..4 -> println("intArr 不在区间1-4中")       // 检测一个值在（in）或者不在（!in）一个区间或者集合中。
            is Int -> println("intArr 是 Int 类型")           // 检测一个值是（is）或者不是（!is）一个特定类型的值。
            !is Int -> println("intArr 是 Int 类型")          // 检测一个值是（is）或者不是（!is）一个特定类型的值。
        }

        // when 也可以用来取代 if-else if链。 如果不提供参数，所有的分支条件都是简单的布尔表达式，而当一个分支的条件为真时则执行该分支
        var x = 123
        when {
            x > 124 -> println("x is odd")
            x < 164 -> println("x is even")
            else -> println("x is funny")
        }
    }

}
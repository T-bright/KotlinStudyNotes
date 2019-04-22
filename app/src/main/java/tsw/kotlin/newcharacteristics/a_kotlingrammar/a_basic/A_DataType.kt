package tsw.kotlin.newcharacteristics.a_kotlingrammar.a_basic

import android.util.Log


/**
 * Kotlin的数据类型
 */
class A_DataType {
    val TAG: String = "A_DataType"

    //基本数据类型
    fun printBasicDataType(): Unit {
        val decimalSystem: Int = 123           //十进制123
        val sixteenBinarySystem: Int = 0x7b  //十六进制123
        val twoBinarySystem: Int = 0b1111011   //二进制123
        val doubelData: Double = 123.5           //Double类型
        val floatData: Float = 123.5F            //Float类型

        //可以使用下划线使数字常量更易读：
        val oneMillion = 1_000_000
        val creditCardNumber = 1234_5678_9012_3456L
        val socialSecurityNumber = 999_99_9999L
        val hexBytes = 0xFF_EC_DE_5E
        val bytes = 0b11010010_01101001_10010100_10010010

        val isTrue = true

        Log.e(TAG,"${twoBinarySystem}")
    }

    //数组类型
    fun array() {
        var a = Array(5, { 0 })                     // 创建指定长度的数组，初始化长度为5，元素均为0.
        for (i in 0..4) {//给数组赋值
            a[i] = i + 1
        }

        var b = Array(5, { 0;1 })         // 创建指定长度的数组，初始化长度为5，元素均为1.
        for (i in 0..4) {//给数组赋值
            b[i] = i + 1
        }

        var c: Array<Int> = Array(5, { i -> i + 1 })            //使用闭包创建数组。初始化长度为5，元素为1,2,3,4,5
        var d: Array<Int> = arrayOf(1, 2, 3, 4, 5)     //使用Kotlin封装方法创建数组,元素为1,2,3,4,5
        var e: IntArray = intArrayOf(1, 2, 3, 4, 5)      //使用Kotlin封装方法创建数组,元素为1,2,3,4,5

        var f: Array<Char> = arrayOf('1', '2', '3')
        var g: CharArray = charArrayOf('1', '2', '3')

        var i: Array<String> = Array(3, { "0" })
        for (n in 0..2) {//给数组赋值
            i[n] = (n + 1).toString()
        }

        //数组的遍历
        var h: Array<String> = arrayOf("1", "2", "3")
        // 遍历数组元素
        for (item in h) {
            Log.e(TAG,"${item}")
        }
        // 遍历数组下标
        for (item in h.indices) {
            Log.e(TAG,"${item}")
        }
        // 迭代器遍历数组1
        val it = h.iterator()
        for (item in it.iterator()) {
            Log.e(TAG,"${item}")
        }
        // 迭代器遍历数组2
        val it1 = h.iterator()
        it1.forEach {
            Log.e(TAG,"${it}")
        }
        // forEach遍历数组
        h.forEach {
            Log.e(TAG,"${it}")
        }
    }

    fun char(c: Char) {
//        if(c == 97){//Kotlin中不可以这么写，编译不通过
//
//        }
        if (c.toInt() == 97) {
            Log.e(TAG,"${c}")
        }
        if (c == 'a') {
            Log.e(TAG,"${c}")
        }
    }

    //字符串操作，和字符串模板操作,
    fun stringOperation() {
        //字符串声明
        var courseName = "谷歌发布TensorFlow Lite，苹果不甘示弱祭出Core ML"
        //输出字符串长度--courseName.length,返回Int类型  属性成员
        println("字符串长度:" + courseName.length)
        //输出字符串是否为空--courseName.isEmpty(),返回Boolean类型 函数（方法）成员
        println("字符串是否为空:" + courseName.isEmpty())
        //输出字符串字符总和--courseName.count(),返回Int类型
        println("字符串字符总和:" + courseName.count())
        //获取指定位置的字符 courseName.get（X） x代表自定位置 或写成 courseName[x] courseName.elementAt(x)
        println(
            "获取指定位置3的字符:" + courseName.get(3) + "\t或者写成courseName[3]:" + courseName[3] + "\t或者写成courseName.elementAt(3):" + courseName.elementAt(
                3
            )
        )
        //获取首字符 courseName.first()
        println("获取首字符:" + courseName.first())
        //获取尾字符 courseName.last()
        println("获取尾字符:" + courseName.last())
        //截取某一段字符 courseName.substring(x) x想代表0到某个指定位置的字符串
        println("截取某一段字符:" + courseName.substring(20))
        //截取中间一段字符 courseName.substring(x，x1) x代表起始位置(x>= 0),x1想代表0到某个指定位置的字符串
        println("截取中间一段字符:" + courseName.substring(10, 20))
        println("*******************************索引**********************************")
        //重新声明--索引
        courseName = "谷歌发布TensorFlow Lite，苹果不甘示弱祭出Core ML"
        //取出关键字 “布” 的 位置
        println("取出关键字 “布” 的 位置:" + courseName.indexOf("布"))
        //用关键字 “indices”显示 courseName的索引范围 ..表示区间范围
        println("用关键字 “indices”显示 courseName的索引范围:" + courseName.indices)
        //最后一个索引位置 +courseName.lastIndex
        println("最后一个索引位置:" + courseName.lastIndex)
        //最后一个索引位置 +courseName.lastIndexOf
//    println("最后一个索引位置:"+courseName.lastIndexOf("祭"))
        println("还原声明:" + courseName.reversed())

        var title = "谷歌发布TensorFlow Lite，苹果不甘示弱祭出Core ML"
        println("*******************************字符串比较**********************************")
        println("方法一(courseName == title):" + (courseName == title))
        println("方法二(courseName.contentEquals(title )):" + (courseName.contentEquals(title)))

        println("*******************************字符串舍弃**********************************")
        println("舍弃前面几个字符（title.drop(6)）:" + title.drop(6))
        println("舍弃后面几个字符（title.dropLast(6)）:" + title.dropLast(6))
        title = "    谷 歌发  布TensorFlow Lite，苹果   不甘示弱 祭出Core   ML    "
        println("舍弃前面字符的空格（title.dropWhile { it.isWhitespace() }）:" + title.dropWhile { it.isWhitespace() })
        println("舍弃前面后面字符的空格 追加(高阶函数)实现\n（title.dropWhile { it.isWhitespace() }.dropLastWhile { it == '弱'||it.isWhitespace() }）:" + title.dropWhile { it.isWhitespace() }.dropLastWhile { it == '弱' || it.isWhitespace() })
        println("*******************************字符串取出**********************************")
        println("取出前面几个字符（title.take(6)）:" + title.take(6))
        println("取出后面几个字符（title.takeLast(6)）:" + title.takeLast(6))
        println("取出空白字符（title.takeLast(6)）:" + title.takeWhile { !it.isWhitespace() })
        println("*******************************字符串替换**********************************")
        println("替换多个字符（courseName.replace）:" + courseName.replace("祭出Core", "XXXXX"))
        println("替换一个为多个字符（courseName.replace）:" + courseName.replace("出", "没有"))
        println("替换一个为一个字符（courseName.replace）:" + courseName.replace("出", "呵"))

        println("*******************************字符串扩展**********************************")
        println("插入title.toCharArray().forEach { print(it+\",\") }：")
        println(title.toCharArray().forEach { print("$it,") })
        println("或者插入title.toCharArray().forEach { print(it+\",\") }：")
        println(title.toCharArray().forEach { print(it + ",") })
//    List<String> title = title.toCharArray().forEach { print(it+",") }.toString().split(",")
        println("特殊插入：")
        var name = "小明"
        var sex = true
        var date = "2012年12月25日"
        var time = "12点32分"
        var cacke = "遛狗"

        val orderInfo = "你好${name}写别${if (sex) "boy" else "girl"}" + "您已于${date}进入${date}\t${time}" + "动作${cacke}"
        println(orderInfo)

        //上面的这一段不知道从哪个地方copy过来的，没找到了----------------------------------------------------------------------------


        val text = """
      |Tell me and I forget.
      |Teach me and I remember.
      |Involve me and I learn.
      |(Benjamin Franklin)
    """
        println(text)


        //字符串模板：各种变量组合成一个动态的字符串，模板表达式以美元符（$）开头，由一个简单的名字构成:
        val i = 10
        println("i等于${i}")
//    原生字符串和转义字符串内部都支持模板。 如果你需要在原生字符串中表示字面值 $ 字符（它不支持反斜杠转义），你可以用下列语法：
        println("小米价格是${'$'}${i}")


        println("*******************************元组（Tuple）**********************************")
//        元组（Tuple），给多个变量同时赋值，分二元（Pair）和三元（Triple）
        val (year, month, day) = Triple(2017, "6月", "14号")
        println("${year}年${month}${day}")
        val date1 = Triple(2017, "6月", "14号")
        println("${date1.first}年${date1.second}${date1.third}")
        //二元同上，把Triple换成Pair
    }


    //空判断 总结
    fun isNull() {
        //申明一个不可为null的String类型变量
        var a: String = "abc" // 这个String类型的变量不可赋值为null
        //a = null; //如果赋值为null编译不通过

        //申明一个可为null的String类型变量
        var b: String? = null// String?   表示String类型的变量可以为null
        //此时，如果想要调用a的相关方法，是没有问题的。但是调用b的相关方法是无法通过编译的，因为可能为null
        var aLength = a.length

        var bLength: Int? = b?.length

        //如果只对某个非空值进行操作，安全调用操作符可以与let一起使用
        var strArray: Array<String?> = arrayOf("A", "B", null)
        for (str in strArray) {
            str?.let {
                Log.e(TAG,"${str}")//只对非空值进行操作
            }
        }

        //如果b为null，就返回-1
        var bLength1: Int = -1;
        if (b != null) {
            bLength1 = b.length
        } else {
            bLength1 = -1
        }

        bLength1 = if (b != null) b.length else -1 //如果b为null，就返回-1

        Log.e(TAG,"${bLength1}")

        //如果 ?: 左侧表达式非空，elvis 操作符就调用其左侧表达式;当左侧表达式为空时，则调用右侧表达式。
        bLength1 = b?.length ?: -1  //如果b为null，就返回-1
        Log.e(TAG,"${bLength1}")


        bLength1 = b!!.length //如果b为空，则抛出NullPointerException异常
        Log.e(TAG,"${bLength1}")

        getStrLength(b)
    }

    //由于 throw 和 return 语句在 Kotlin 中都是表达式，所以它们也可以用在 elvis 操作符右侧。
    private fun getStrLength(b: String?): Int {
        val bLength2 = b?.length ?: return -1
        val bLength3 = b?.length ?: throw NullPointerException("b is null")
        return bLength3
    }

    //安全的类型转换
    fun isClassCastException(){
        var b : String= "aaa"
        var bb : Int? = b as? Int//不会抛异常
        var bbb : Int = b as Int//会抛异常
        Log.e(TAG,"${if(bb != null)bb else -1}")
    }
}
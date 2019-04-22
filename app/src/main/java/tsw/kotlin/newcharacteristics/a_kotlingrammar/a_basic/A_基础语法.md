# 一、Kotlin的数据类型

## 1、数值类型
kotlin的基本数值类型包括Byte、Short、Int、Long、Float、Double等。不同于java，字符不属于数值类型，是一个独立的数据类型。

| 类型     | 位宽度 | 
| :-:| :-: |
Double|	64
Float	|32
Long	|64
Int	|32
Short	|16
Byte	|8

- 字面常量
  - 十进制 ：123
  - 长整型以大写的L结尾：123L
  - 16进制以0X开头：0x0F
  - 2进制以0b开头：0b00001011
  - ***8进制不支持***

- 浮点数值
  - Double的默认写法：123.5,  123.5e10
  - Float使用f或者F结尾：123.5f 
  
详细请看代码  ：
```
val decimalSystem : Int= 123             //十进制123
val sixteenBinarySystem : Int =  0x7b    //十六进制123
val twoBinarySystem : Int= 0b1111011     //二进制123
val doubelData :Double = 123.5           //Double类型
val floatData :Float= 123.5F             //Float类型
        
//上面的代码也可以写成这样
val decimalSystem = 123           //十进制123
val sixteenBinarySystem  =  0x7b  //十六进制123
val twoBinarySystem = 0b1111011   //二进制123
val doubelData  = 123.0           //Double类型
val floatData = 123.0F            //Float类型
```
  
上述代码也可以使用下划线使得数值常量更加易读
```
val oneMillion = 1_000_000
val creditCardNumber = 1234_5678_9012_3456L
val socialSecurityNumber = 999_99_9999L
val hexBytes = 0xFF_EC_DE_5E
val bytes = 0b11010010_01101001_10010100_10010010
```

## 2、布尔类型
Kotlin的布尔类型和java一样只有两个值，用true和false表示。
```
val isTrue : Boolean = true
val isFalse : Boolean= false
```
## 3、数组类型
数组用类 Array 实现，并且还有一个 size 属性及 get 和 set 方法，由于使用 [] 重载了 get 和 set 方法，所以我们可以通过下标很方便的获取或者设置数组对应位置的值。
数组的创建两种方式：一种是使用函数arrayOf()；另外一种是使用工厂函数。如下所示，我们分别是两种方式创建了两个数组：
- 数组的创建
```
var a = Array(5,{0})                           // 创建指定长度的数组，初始化长度为5，元素均为0.
for (i in 0..4) {//给数组赋值
    a[i] = i+1
}

var b = Array(5,{0;1})                         // 创建指定长度的数组，初始化长度为5，元素均为1.
for (i in 0..4) {//给数组赋值
    b[i] = i+1
}
        
var c : Array<Int> = Array(5,{ i -> i+1})     //使用闭包创建数组。初始化长度为5，元素为1,2,3,4,5
var d : Array<Int> = arrayOf(1, 2, 3,4,5)     //使用Kotlin封装方法创建数组,元素为1,2,3,4,5
var e : IntArray = intArrayOf(1,2,3,4,5)      //使用Kotlin封装方法创建数组,元素为1,2,3,4,5
        
//其它基本类型的数组声明与之类似，只要把int替换为long、float、double、boolean、char即可。
var f: Array<Char> = arrayOf('1', '2', '3')
var g: CharArray = charArrayOf('1', '2', '3')
        
//这里还有一个注意的点,String类型的数组只能通过arrayOf或者Array去创建，没有stringArrayOf
var h: Array<String> = arrayOf("1", "2", "3")
var i: Array<String> = Array(3, { "0" })
for (n in 0..2) {//给数组赋值
    i[n] = (n+1).toString()
}
           
```
- 数组的遍历
```
var h: Array<String> = arrayOf("1", "2", "3")
// 遍历数组元素
 for (item in h) {
     println(item)
 }
// 遍历数组下标
for (item in h.indices) {
    println(item)
}
// 迭代器遍历数组1
val it = h.iterator()
for (item in it.iterator()) {
    println(item)
}
// 迭代器遍历数组2
val it1 = h.iterator()
it1.forEach {
      println(it)
}
// forEach遍历数组
h.forEach {
    println(it)
}
```

## 4、字符类型
和 Java 不一样，Kotlin 中的 Char 不能直接和数字操作，Char 必需是单引号 ' 包含起来的。比如普通字符 '0'，'a'。
```
    fun check(c : Char){
//        if(c == 97){//Kotlin中不可以这么写，编译不通过
//
//        }
        if(c.toInt() == 97){
            println(c)
        }
        if(c == 'a'){
            println(c)
        }
    }
    
    字符字面值用单引号括起来: '1'。 特殊字符可以用反斜杠转义。 
    支持这几个转义序列：\t-->制表符;\b-->退格符;\n-->换行符;\r-->回车符;\'-->单引符;\"-->双引符;\\-->反斜杠。
    编码其他字符要用 Unicode 转义序列语法：'\uFF00'。
```

## 5、字符串类型
字符串 用String 类型表示。字符面值用双引号括起来："我是字符串"，如果是转义字符串，可以参考java，很像。
字字符串是不可变的。 字符串的元素——字符可以使用索引运算符访问: s[i]。
```
 val s = "Hello, world!"
```
- 原生字符串 使用三个引号（"""）分界符括起来，内部没有转义并且可以包含换行和任何其他字符:
```
 val text = """
      |Tell me and I forget.
      |Teach me and I remember.
      |Involve me and I learn.
      |(Benjamin Franklin)
    """
```
- 字符串相加 ；字符串和字符相加
```
var str = "one"+ 'd'+ "two" + 't'

```
-  元组（Tuple），给多个变量同时赋值，分二元（Pair）和三元（Triple）
```
        val (year ,month,day) = Triple(2017,"6月","14号")
        println("${year}年${month}${day}")
        val date1 = Triple(2017,"6月","14号")
        println("${date1.first}年${date1.second}${date1.third}")
        //二元同上，把Triple换成Pair
```

## 6、字符串模板
```
//字符串模板：各种变量组合成一个动态的字符串，模板表达式以美元符（$）开头，由一个简单的名字构成:
        val i = 10
        println("i等于${i}")
//原生字符串和转义字符串内部都支持模板。 如果你需要在原生字符串中表示字面值 $ 字符（它不支持反斜杠转义），你可以用下列语法：
        println("小米价格是${'$'}${i}")
```

# 二、Kotlin的数据类型操作
## 1、null判断总结
- 申明是否可以为null的字符串
```
        //申明一个不可为null的String类型变量
        var a: String = "abc" // 这个String类型的变量不可赋值为null
        a = null; //如果赋值为null编译不通过

        //申明一个可为null的String类型变量
        var b: String? = null// String?   表示String类型的变量可以为null
        
        //此时，如果想要调用a的相关方法，是没有问题的。但是调用b的相关方法是无法通过编译的，因为可能为null
        var aLength = a.length
        var bLength = b.length//无法通过编译
```
- 空字符串的处理

   对于上面 ```var b: String? = null```申明的字符串，我们应该怎么去处理呢？
   
  - 判断是否为空，不为空则访问其成员属性和方法
     ```
     if(b != null){
       var bLength = b.length//通过编译
     }
     ```
  - Elvis 操作符,```?:```
    有点类似于java 的三元运算符
    ```
    //如果b为null，就返回-1
    var bLength1: Int = -1;
    if (b != null) {
       bLength1 = b.length
    } else {
       bLength1 = -1
    }
    
    bLength1 = if (b != null) b.length else -1 //如果b为null，就返回-1
    
    //如果 ?: 左侧表达式非空，elvis 操作符就调用其左侧表达式;当左侧表达式为空时，则调用右侧表达式。
    bLength1 = b?.length ?: -1  //如果b为null，就返回-1
    
    
    //由于 throw 和 return 语句在 Kotlin 中都是表达式，所以它们也可以用在 elvis 操作符右侧。
    fun getStrLength(b: String?): Int {
       val bLength2 =  b?.length ?: return -1
       val bLength3 =  b?.length ?: throw NullPointerException("b is null")
    return bLength3
    }
    ```
    
  - 安全调用，安全调用操作符: ```?.```
     ```
      var bLength: Int? = b?.length
     ```
     如果 b 非空，就返回 b.length；否则返回 null,而不是出现 NullPointerException 。
     如果只对某个非空值进行操作，安全调用操作符可以与let一起使用
     ```
     var strArray: Array<String?> = arrayOf("A", "B", null)
     for (str in strArray) {
          str?.let {
             println(str)//只对非空值进行操作
          }
      }
     ```
  - !! 操作符,这个操作符的作用就是当字符串为空的时候，调用相关方法时就会抛出NullPointerException异常
     ```
     var b: String? = null
     var bLength1: Int = -1;
     bLength = b!!.length          //如果b为空，则抛出NullPointerException异常
     ```
  -  安全的类型转换
     当我们对一个对象进行强转的时候，若对象不是目标类型，则会抛出ClassCastException异常。
     如果我们使用安全的类型转换,则不会抛出ClassCastException异常，会返回一个null
     ```
     var b = null
     var bb : Int = b as Int       //会抛出ClassCastException异常
     var bbb : Int? = b as? Int     //不会抛出ClassCastException异常
     ```
  
     

Kotlin 类可以包含：构造函数和初始化代码块、函数、属性、内部类、对象声明。
Kotlin 中使用关键字 class 声明类，后面紧跟类名
```
class D_ClassesAndObjects {
}
```

 
# 一、类的构造
Kotlin的构造函数分为主构造函数和二级构造函数,代码如下
```
//类名后面的constructor可以省略，
// 但是如果构造器有注解，或者有可见度修饰符，这时constructor关键字是必须要的，注解和修饰符要放在它之前。
//class D_ClassesAndObjects constructor(name : String ,sex : String){
class D_ClassesAndObjects(name: String, sex: String) {
    init {
        println("这是主构造函数的内部方法，name = ${name} ,sex = ${sex}")
    }

    constructor(name: String, sex: String, age: Int) : this(name, sex) {
        println("这是二级构造函数的内部方法，name = ${name} ,sex = ${sex},age = ${age}")
    }
}
```
通过代码可以看到，二级构造函数调用了主构造函数，所以当我们以下面的方式去实例化对象的时候，主构造函数的代码块init方法也会
执行
```
var dClassesAndObjects = D_ClassesAndObjects("张三","男",15)
    
//打印结果
这是主构造函数的内部方法，name = 张三 ,sex = 男
这是二级构造函数的内部方法，name = 张三 ,sex = 男,age = 15
```
还有一个显著的特点就是构造方法可以有默认参数

```
class D_ClassesAndObjects(name: String, sex: String) {
    init {
        println("这是主构造函数的内部方法，name = ${name} ,sex = ${sex}")
    }

    constructor(name: String, sex: String, age: Int = 8) : this(name, sex) {
        println("这是二级构造函数的内部方法，name = ${name} ,sex = ${sex},age = ${age}")
    }
}

//实例化对象
 var dClassesAndObjects = D_ClassesAndObjects("张三","男")
 
打印结果
这是主构造函数的内部方法，name = 张三 ,sex = 男
//-------------------------------------------------------------------------------
//实例化对象
 var dClassesAndObjects = D_ClassesAndObjects("张三","男",7)
 
打印结果
这是主构造函数的内部方法，name = 张三 ,sex = 男
这是主构造函数的内部方法，name = 张三 ,sex = 男,age = 7

```
我们可以看到，在调用二级构造函数的时候，肯定会先调用主构造函数。
但是像Android自定义View时候，一般都有3个构造函数，如果此时调用二级构造函数，那么主构造函数又要调用一次，这样做不够完美。
这个的问题有两种解决方式：
   - 1、因为Kotlin的主构造函数不是必须的，所以我们可以不用写主构造函数，只写二级构造函数，这样就不会出现上述问题了。代码如下
      ```
      class D_ClassesAndObjects {
      
          constructor (name: String, sex: String) {
              println("这是二级构造函数的内部方法，name = ${name} ,sex = ${sex}")
          }
      
          constructor(name: String, sex: String, age: Int = 8) {
              println("这是二级构造函数的内部方法，name = ${name} ,sex = ${sex},age = ${age}")
          }
      }
      
       //实例化对象
       var dClassesAndObjects = D_ClassesAndObjects("张三","男",7)
       
       //打印
       这是主构造函数的内部方法，name = 张三 ,sex = 男,age = 7
      ```
   - 2、Kotlin有默认参数，可以解决这个问题
      ```
      class D_ClassesAndObjects constructor(name: String, sex: String, age: Int = 8) {
      
          init {
              println("这是主构造函数的内部方法，name = ${name} ,sex = ${sex},age = ${age}")
          }
          
      }
      
      //实例化对象
      var dClassesAndObjects = D_ClassesAndObjects("张三","男")
      var dClassesAndObjects = D_ClassesAndObjects("张三","男",7)
      
      //打印
      这是主构造函数的内部方法，name = 张三 ,sex = 男,age = 8
      这是主构造函数的内部方法，name = 张三 ,sex = 男,age = 7       
      ```
      
# 二、类的成员
- 1、kotlin类的成员的声明：用关键字 var和val表示，如下代码
```
   var name : String = "张三" //用var修饰表示该字段的值是可以改变的
   val sex : String = "男"    //用val修饰表示该字段的值是不可以改变的，类似于java中用final修饰了
```
还有一种是在主构造函数上声明的(二级函数上不可以声明)，也是用var和val表示，如下代码
```
class D_ClassesAndObjects constructor(var name: String, val sex: String, age: Int = 8) {
    var mAddress: String = "";
    
    init {
        println("这是主构造函数的内部方法，name = ${name} ,sex = ${sex},age = ${age}")
    }
    
    fun setAddress(address: String) {//成员方法
        mAddress = address
        println("这是setAddress方法，name = ${name} ,mAddress = ${mAddress}")
    }
}
```
- 2、成员方法的调用
直接上代码，语法和java类似
```
class D_ClassesAndObjects constructor(var name: String, val sex: String, age: Int = 8) {
    var mAddress: String = "";

    init {
        println("这是主构造函数的内部方法，name = ${name} ,sex = ${sex},age = ${age}")
    }

    fun setAddress(address: String) {
        mAddress = address
        println("这是setAddress方法，name = ${name} ,mAddress = ${mAddress}")
    }
}

//-------------------下面是调用代码------------------------------------------------------
var dClassesAndObjects = D_ClassesAndObjects("张三","男")
dClassesAndObjects.setAddress("上海")
println("获取成员属性，name = ${dClassesAndObjects.name}")
    
//-------------------下面是打印结果-----------------------------------------------------
这是主构造函数的内部方法，name = 张三 ,sex = 男,age = 8
这是setAddress方法，name = 张三 ,mAddress = 上海
获取成员属性，name = 张三
```
可以看到调用成员方法和成员属性只要用下面的方式调用就行了
```
实例名称.方法名称(输入参数)
实例名称.成员名称
```

- 3、类似JAVA的那种静态方法调用：类的名称.方法名称(输入参数)、类的名称.成员名称
kotlin引入了一个新概念:***伴生对象(companion )*** ,它是在类加载的时候就会运行，相当于java语言中的static{...}代码块。
一个类只有一个伴生对象，但类的实例却有很多个，这是两者之间的区别。代码如下
```
class D_ClassesAndObjects {
    companion object CompanionObject {
        val MANFLAG = 0;
        val MAN = "男"
        val WOMAN = "女"
        val TRANSGENDER = "变性人"
        fun isMan(flag : Int){
            when (flag){
                0 -> println(MAN)
                1 -> println(WOMAN)
                else -> println(TRANSGENDER)
            }

        }
    }
}

//-------------------下面是调用代码------------------------------------------------------
//D_ClassesAndObjects.CompanionObject.isMan(D_ClassesAndObjects.MANFLAG)//伴生对象的名字可以省略
D_ClassesAndObjects.isMan(D_ClassesAndObjects.MANFLAG)

//-------------------下面是打印结果-----------------------------------------------------
男
```
所以我们可以根据伴生的对象的性质，去做一些常量的声明，或一些工具类

# 三、类的继承
类的继承相关点如下：
- 普通类默认不能被继承，抽象类默认是可以被继承的，如果要使得某个普通类可以被继承，则要用open关键字。
- 接口不能有构造函数,但是可以在接口内实现某个方法
- Kotlin的继承和实现都用冒号（:）表示，中间用逗号（,）隔开
- Kotlin可以多实现，但是不可以多继承
- Kotlin除了这三个开放性修饰符public、protected、private外，还引入了一个新的修饰符internal。
    - public    ：对所有人开放。Kotlin的类、函数、变量不加开放性修饰符的话，默认就是public类型。
    - protected ：只对自己和子类开放。
    - private   ：只对自己开放。
    - internal  ：只对本模块内部开放，这是Kotlin新增的关键字。对于App开发来说，本模块便是指App自身。
    
    
详细请看代码
```
interface AnimalSleep {
    fun whenGoToSleep(): String
}

interface AnimalBellow {
    fun animalVellow(): String;//动物的吼叫声
    fun fly(): String {   //Kotlin允许在接口内实现某个方法
        return "只有鸟会飞"
    }
}

abstract class AnimalBase(sex : String) {
    abstract fun getSex(): String
}

abstract class Animal(sex: String, name: String) : AnimalBase(sex) {
    abstract fun getName(): String
}

//单继承，多实现
open class Dog(sex: String,name : String, val run: String) : Animal(sex,name), AnimalBellow, AnimalSleep {
    var mSex = sex;
    var mName = name;
    override fun getName(): String {
        return mName
    }

    override fun whenGoToSleep(): String {
        return "有空就睡"
    }

    override fun animalVellow(): String {
        return "汪汪汪"
    }

    override fun fly(): String {
        return "狗会坐飞机"
    }

    override fun getSex(): String {
        return mSex;
    }

    open fun howToRun(){
        println("${mName}跑啊跑,${run}")
    }
}

class MaleDog constructor( run: String,sex: String = "公", name: String = "旺财") : Dog(sex, name, run) {
    fun eat() {
        println("${mName}真能吃")
    }

    override fun howToRun() {
        println(run)
    }
}
```

# 四、特殊类
Kotlin新增了几个特殊类，这些特殊类用在不同的场景，可以大幅度提升撸码的效率。
## 1、嵌套类
在一个类中定义一个类，如SpecificClassOne，这个类叫着嵌套类,嵌套类不能访问外部类的任何属性和方法.
```
class SpecificClass(var name : String = "我是特殊类"){
    var name: String = ""

    //嵌套类
    class SpecificClassOne {
        //在一个类中定义一个类，如SpecificClassOne，这个类叫着嵌套类
        //嵌套类不能访问外部类的任何属性和方法
        fun getClassName(): String {
            //return name // 编译期无法通过，因为嵌套类无法访问外部类的任何属性和方法
            return "我是嵌套类"
        }
    }
}

```
***嵌套类的实例化：嵌套类的类名前面添加外部类的类名***
```
var specificClassOne = SpecificClass.SpecificClassOne()
specificClassOne.getClassName()
```
## 2、内部类
刚才说了嵌套类无法调用到外部类的成员属性和方法，那么怎么去调用呢？那就是在嵌套类的class前面加一个inner关键字，
将嵌套类转变成内部类
```
class SpecificClass(var name : String = "我是特殊类") {
    //内部类
    inner class SpecificClassTwo {
        fun getName(): String {
            return name
        }
    }
}
```
***内部类的实例化：先实例化外部类，然后通过外部类的实例对象去调用内部类的构造函数***
```
//内部类的调用
var specificClassTwo = SpecificClass("内部类").SpecificClassTwo()
specificClassTwo.getName()
```
## 3、数据类
在开发中经常使用到存放数据的实体类，如：存放用户信息的类。因此，Kotlin推出了名为数据类的特殊类，用data修饰。Kotlin数据类有以下几个特点：
- 数据类必须要有主构造函数，且至少有一个参数，参数前面必须添加var或者val
- 数据类不能被继承也不能继承其他类，数据类不能是内部类、抽象类、密封类,可以是嵌套类
- 自动提供equals方法，用于比较两个数据对象是否相等；
- 自动提供copy方法，允许完整复制某个数据对象，也可在复制后单独修改某几个字段的值；
- 自动提供toString方法，用于打印数据对象中保存的所有字段值；

```
data class DataClass(var name: String, var sex: String, var age: Int){

}
```
相关调用
```
var dataClassOne = DataClass("张三","男",14)
var dataClassTwo = dataClassOne.copy()
var dataClassThree =dataClassOne.copy(name = "李四",sex = "女")
println(dataClassOne)
println(dataClassTwo)
println(dataClassThree)
println(dataClassOne.equals(dataClassTwo))
println(dataClassOne.equals(dataClassThree))
```
打印结果
```
DataClass(name=张三, sex=男, age=14)
DataClass(name=张三, sex=男, age=14)
DataClass(name=李四, sex=女, age=14)
true
false
```
## 4、枚举类
基本的枚举类代码如下
```
enum class EnumClasOne {
    RED,
    BLUE,
    BLACK
}
```
上述是基本的写法，但是在Kotlin中枚举值后面还是可以带一个枚举值的特征描述。写法如下
```
enum class EnumClasTwo(val describe: String) {
    RED("红色"),
    BLUE("蓝色"),
    BLACK("黑色")
}
```

调用代码
```
//ordinal表示枚举类型的序号，name表示枚举类型的名称
for(i in 0..2){
    when(i){//普通枚举类
        EnumClasOne.RED.ordinal ->  println(EnumClasOne.RED.name)
        EnumClasOne.BLUE.ordinal ->  println(EnumClasOne.BLUE.name)
        EnumClasOne.BLACK.ordinal ->  println(EnumClasOne.BLACK.name)
        else -> println("不知道什么鬼颜色")
    }
}

for(i in 0..2){
    when(i){//普通枚举类
        //不用name，用自定义属性describe
        EnumClasTwo.RED.ordinal ->  println(EnumClasTwo.RED.describe)
        EnumClasTwo.BLUE.ordinal ->  println(EnumClasTwo.BLUE.describe)
        EnumClasTwo.BLACK.ordinal ->  println(EnumClasTwo.BLACK.describe)
        //枚举类虽然也可以添加一个主构造函数，但是这个主构造函数不是给别人用的，是给自己用的。外部类是没法调用枚举类的构造函数
//      else -> EnumClasTwo("什么鬼颜色")
        else -> println("不知道什么鬼颜色")
    }
}
```
打印结果
```
RED
BLUE
BLACK
红色
蓝色
黑色
```
## 5、密封类
密封类用关键字 ***sealed*** 去申明。密封类其实可以理解成枚举类，它定义了有限个的类型，不存在其他的类型。它与枚举类的区别就是枚举常量只存在一个
一个实例，而密封类的子类却可以存在多个实例。
密封类具有以下几个特点：
 - 密封类的构造方法是私有的
 - 密封类可以被继承，但是继承的类必须和密封类在同一个文件中
 - 密封类的子类，也是可以被继承的，可以在任何地方，没有必须在同一个文件中的限制
 
使用密封类的好处就是，使用when表达式的时候，如果验证了所有情况，就不需要用else分支了
代码如下
```
//密封类
sealed class SealedClass {
    val testOne: String = "111"

    //密封类可以有子类，但是子类必须和密封类在同一个文件中，可以在密封类的内部，也可以在密封类的外部
    //SealedClassTwo可以被继承，可以在任何地方，不需要和密封类在同一个文件中
    open class SealedClassTwo : SealedClass() {
        val testTwo: String = "222"
    }

    object SealedClassThree : SealedClass()
}

//密封类可以有子类，但是子类必须和密封类在同一个文件中，可以在密封类的内部，也可以在密封类的外部
//SealedClassFour可以被继承，可以在任何地方，不需要和密封类在同一个文件中
open class SealedClassFour : SealedClass()
```
使用代码
```
var sealedClassTwo = SealedClass.SealedClassTwo()
var sealedClassFour = SealedClassFour()
var list = arrayOf(sealedClassTwo, sealedClassFour, SealedClass.SealedClassThree.testOne)
list.forEach {
     when (it) {
          is SealedClass.SealedClassTwo -> println("SealedClassTwo")
          is SealedClassFour -> println("SealedClassFour")
          "111" -> println("testOne")
          //如果包含所有条件，就不需要些else了
     }
}
```

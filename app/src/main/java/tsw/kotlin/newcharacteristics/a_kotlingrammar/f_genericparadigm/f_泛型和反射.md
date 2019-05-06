# 一、泛型
### 1、泛型的写法
Kotlin泛型和Java差不多，代码写法如下
```
class TestOneClass<T>(t: T) {
    var value = t
}

class TestTwoClass<T : GenericParadigm>(t: T) {
    var value = t
}

class TestThreeClass : GenericParadigm(){
    override var value = "TestThreeClass"
}
```

调用代码如下
```
var testOne = TestOneClass<String>("aaaa")
log(testOne.value)

testOne = TestOneClass("bbbbb")//可以简写成这样，编译期可以自动推断类型
log(testOne.value)

//如果要接收一个null,编译器无法推断类型，则必须要指定类型
var ss = TestOneClass<String?>(null)
log(ss.value.toString())
```

### 2、泛型的约束
泛型约束就是限定参数使用的类型，而不是任意类型的，如：
```
class TestTwoClass<T : GenericParadigm>(t: T) {
    var value = t
}
```
此时的T只能是GenericParadigm或者其子类的类型，不能是其他类型，否则编译期无法通过。调用代码如下
```
var testTwoClass = TestTwoClass<TestThreeClass>(TestThreeClass())
var testThreeClass = testTwoClass.value
log(testThreeClass.value)
```
当然，可能也会出现T有多个上限的情况。比如说T上限是CharSequence和Comparable<T>类型的，那如何去申明这个方法呢？可以用where表示，代码如下：
```
//下面的T的上界是CharSequence和Comparable<T>，而String的上界（父类）是CharSequence和Comparable<String>，所以可以穿String类型的
fun <T> copy(list: List<T>, thresold: T): List<String>
       where  T : CharSequence, T : Comparable<T> {
    return list.filter { it > thresold }.map { it.toString() }
}
```
调用代码
```
//可以穿String类型的。不可以穿Int类型，因为Int类型上界（父类）是Number和Comparable<Int> ，不符合
copy(listOf<String>("1","5","6"),"4")
```

### 3、泛型的型变
Kotlin与Java不同的是，Kotlin没有通配符类型，但它有两个另外的东西： 声明处型变（declaration-site variance）与类型投影（type projections）。
- 声明处型变

  声明处的类型变异使用协变注解修饰符：in(表示其或者其父类)、out(表示其或者其子类)
  - in：表示类型参数逆变。逆变类型参数不能用作输出，不能作为返回值类型但是可以作为入参的类型。逆变类型限定其或者其父类
  - out：表示类型参数协变。协变类型参数只能用作输出，可以作为返回值类型但是无法作为入参的类型。协变类型限定其或者其子类
  
  代码如下
  ```
  //逆变,其或其父类
  class TestFiveClass<in T>(t: T) {
      var value = "TestFiveClass"
      fun getType(t : T) {//逆变可以做入参，不能做返回值
  
      }
  }
  //协变,其或其子类
  class TestSixClass<out T>(val t: T) {
      var value = "TestSixClass"
      fun getType(): T {//协变不可以做入参，可以用来做返回值
          return t
      }
  }
  ```
   调用代码
   ```
   //型变
   fun threeTest() {
       //逆变类型限定其或其父类
       var genericParadigmNB = TestFiveClass<GenericParadigm>(GenericParadigm())
       var testThreeClassNB = TestFiveClass<TestThreeClass>(TestThreeClass())
       var testFourClassNB = TestFiveClass<TestFourClass>(TestFourClass())
       testThreeClassNB = genericParadigmNB
       //下面这行代码编译不通过，因为逆变类型限定其或其父类，而TestFourClass是TestThreeClass的子类，所以编译不通过
       //testThreeClassNB = testFourClassNB
   
   
       //协变类型限定其或其子类
       var genericParadigmXB = TestSixClass<GenericParadigm>(GenericParadigm())
       var testThreeClassXB =TestSixClass<TestThreeClass>(TestThreeClass())
       var testFourClassXB =TestSixClass<TestFourClass>(TestFourClass())
       testThreeClassXB = testFourClassXB
       //下面这行代码编译不通过，因为协变类型限定其或其子类，而GenericParadigm是TestThreeClass的父类，所以编译不通过
       //testThreeClassXB = genericParadigmXB
   }
   ```
- 类型投影
  
  那什么是类型投影呢？先看看下面代码：
  
  声明一个泛型类
  ```
  class TestOneClass<T>(t: T) {
       var value = t
       fun merge(one: TestOneClass<T>, two: TestOneClass<T>) {

       }
  }
  ```
  调用方法
  ```
  fun fourTest() {
      var parameterStr = TestOneClass<String>("11")
      val parameterAny = TestOneClass<Any>("")
      parameterAny.merge(parameterStr,parameterAny) //此处编译器报错
  }
  ```
   上面调用merge方法，编译器无法通过，这是为什么？String的父类是Any，为什么这里会报错？因为TestOneClass<T>的类型参数是不可变的，
   TestOneClass<String>和TestOneClass<Any>它们没有任何关系，它们之间可能会出现一些不安全的操作，比如说 类转换异常。
   
   那应该怎么解决这个问题呢？可以使用上面说的in或者out解决。代码如下：
   ```
   class TestOneClass<T>(t: T) {
     var value = t
     //out表示协变，类型限定其或者其子类。此时参数one的类型必须是参数two的子类或者 参数one和参数two的类型一样
     fun merge(one: TestOneClass<out T>, two: TestOneClass<T>) {

     }
   }
   
   //代码调用
    fun fourTest() {
        var parameterStr = TestOneClass<String>("11")
        val parameterAny = TestOneClass<Any>("")
        parameterAny.merge(parameterStr,parameterAny) //参数one的类型String是参数two的类型Any的子类
    }
   ```
  那如果用in，又该如何呢？in表示逆变，类型限定其或者其父类。代码如下：
  ```
  class TestOneClass<T>(t: T) {
      var value = t
      fun merge(one: TestOneClass<in T>, two: TestOneClass<T>) {
            
      }
  }
  
  //此时调用方式如下
  fun fourTest() {
      var parameterStr = TestOneClass<String>("11")
      val parameterAny = TestOneClass<Any>("")
      parameterStr.merge(parameterAny,parameterStr) //参数one的类型String是参数two的类型Any的子类
  }
  ```
  
  看到这里，我们将上面这种one的参数类型使用in或者out修饰称作 **类型投射**
  
  
- 星号投射

  有些时候, 你可能想表示你并不知道类型参数的任何信息, 但是仍然希望能够安全地使用它. 这里所谓"安全地使用"是指, 对泛型类型定义一个类型投射, 要求这个泛型类型的所有的实体实例, 都是这个投射的子类型。

  对于这个问题, Kotlin 提供了一种语法, 称为 星号投射(star-projection):

   假如类型定义为 Foo<out T> , 其中 T 是一个协变的类型参数, 上界(upper bound)为 TUpper ,Foo<> 等价于 Foo<out TUpper> . 它表示, 当 T 未知时, 你可以安全地从 Foo<> 中 读取TUpper 类型的值.
   假如类型定义为 Foo<in T> , 其中 T 是一个反向协变的类型参数, Foo<> 等价于 Foo<inNothing> . 它表示, 当 T 未知时, 你不能安全地向 Foo<> 写入 任何东西.
   假如类型定义为 Foo<T> , 其中 T 是一个协变的类型参数, 上界(upper bound)为 TUpper , 对于读取值的场合, Foo<*> 等价于 Foo<out TUpper> , 对于写入值的场合, 等价于 Foo<in Nothing> .
  
   如果一个泛型类型中存在多个类型参数, 那么每个类型参数都可以单独的投射. 比如, 如果类型定义为interface Function<in T, out U> , 那么可以出现以下几种星号投射:
   - Function<*, String> , 代表 Function<in Nothing, String> ;
   - Function<Int, *> , 代表 Function<Int, out Any?> ;
   - Function<, > , 代表 Function<in Nothing, out Any?> .
   
   注意: 星号投射与 Java 的原生类型(raw type)非常类似, 但可以安全使用
   
   上面关于星号投射的解释摘至网络。
   
   由此可以得出 * 相当于 out Any? 、in Nothing。具体的看代码示例
     ```
     fun fiveTest() {
        var parameterStr = TestOneClass<String>("11")
        val arrayListAny: ArrayList<Any?> = arrayListOf("a", 1, 'c')
        arrayListAny.add(parameterStr)

        val arrayListX: ArrayList<*> = arrayListOf("a", 1, 'c')
        arrayListX.add(parameterStr) // 编译器报错

        val arrayListo: ArrayList<out Any?> = arrayListOf("a", 1, 'c')
        arrayListo.add(parameterStr)  // 编译器报错
     }
     ```
   ArrayList<*>表示包含某种特定的类型，但是这个类型目前不知道，所以不能随便写入，因为可能会出现不安全操作，比如类型转换异常。
   
   ArrayList<Any?> 表示包含任何类型的元素，所以你可以随便写入。

# 二、反射
在Kotlin中字节码对应的类是kotlin.reflect.KClass。因为Kotlin是100%兼容java的，所以在kotlin中怎么使用java的class反射对象呢？

比如说 Intent ，它需要的是一个java类型的class对象，如果传一个Kotlin类型的kClass对象，肯定不行。

代码如下
```
fun sixTest() {
    //-----------------------------获取kotlin的Kclass对象------------------------
    //1.通过类::class的方式获取Kclass实例-
    val kClass: KClass<GenericParadigm> = GenericParadigm::class

    //2.通过实例.javaClass.kotlin获取Kclass实例
    val genericParadigm = GenericParadigm()
    val kClass1: KClass<GenericParadigm> = genericParadigm.javaClass.kotlin

    //-----------------------------在kotlin中获取java的class对象------------------------
    //1.通过类Kclass类的.java属性
    val clazz = GenericParadigm::class.java

    //2.通过实例.javaClass获取java的class对象
    val javaClass : Class<GenericParadigm> = genericParadigm.javaClass
 }
```



























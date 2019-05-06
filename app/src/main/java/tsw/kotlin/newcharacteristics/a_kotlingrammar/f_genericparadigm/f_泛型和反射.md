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
- 星号投射































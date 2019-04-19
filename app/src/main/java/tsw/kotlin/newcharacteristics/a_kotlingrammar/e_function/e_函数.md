# 一、函数的基本用法
先看一个函数的基本申明
## 1、无输入无输出参数
  ```
   public fun noInputAndNoOutPut_Parameters(): Unit {

   }
  ```
  上面是一个无参无返回值的函数。public表示这个函数是公开的，而Kotlin默认就是public，所以public可以不用写。
   ***Unit*** 表示一个函数没有返回值，类似于java中的void，Kotlin中Unit也可以省略不写。

## 2、无输入有输出参数
  ```
   public fun noInputAndHaveOutPut_Parameters(): String {
        return "无输入有输出参数"
   }
  ```
  这是一个有返回值的函数，返回值是String类型的。如果返回值是Boolean类型的，把String换成Boolean即可。其他返回值类型，同理。

## 3、有输入有输出参数
   ```
    public fun hvaeInputAndHaveOutPut_Parameters(input: String): String {
        return "有输入有输出参数：${input}"
    }
   ```
   这里主要是传参的写法，跟java相反，并且多了一个冒号":"。
   其实在这里应该已经发现了 Kotlin变量和函数申明的一个共同点：如下
   ```
   var str : String
   
   fun noInputAndHaveOutPut_Parameters(): String 
   
   fun noInputAndNoOutPut_Parameters(): Unit
   ```
   是不是很像，var对应fun；str对应noInputAndHaveOutPut_Parameters()。当然函数有一个不需要返回值的情况，然而Kotlin中的函数是必须有返回值的，不需要返回值的情况，用 ***Unit*** 表示。
   传入的参数也是，很好记忆。
   
## 4、输入的参数可以为空
  
  如果给上面的函数传参
  ```
  FunctionData().hvaeInputAndHaveOutPut_Parameters(null)
  ```
  这种写法，编译期是无法通过的，那如果我想传入一个值为 null 的参数怎么办？可以在后面加一个 "?"，写法如下
  ```
  public fun parameterCanBeNull(intut: String?): String {//传的参数intut可以为null
     return "有输入有输出参数 ： ${intut}"
  }
  ```
  
## 5、默认参数

  Kotlin中参数是可以有默认值的，代码如下：
  ```
  fun defaultParameter(one: String = "默认参数one", two: String = "默认参数two", three: String = "默认参数three"): String {
      return "拼接后的字符串 ： ${one}; ${two} ; ${three} "
  }
  ```
  那如何调用呢？如果我要传的参数和默认的参数一样，该怎么传？
  ```
  FunctionData().defaultParameter() //这种的就是默认传的默认参数
  
  FunctionData().defaultParameter("不是默认参数one") //这个修改的第一个参数one，后面two和three仍然是默认值
  
  
  //如果想要修改第二个参数two，其他的仍然是默认值，可以用下面这种方式进行传值。调用的时候指定参数名称：格式 参数名 = 参数值
  FunctionData().defaultParameter(two = "不是默认参数two")
  ```

## 6、可变参数
  
  有时候我们需要传入的参数的个数是不确定的，此时我们可以使用 可变参数，用vararg 修饰。代码如下
  ```
  fun changeParameter(vararg args: String): String {
    var stringBuilder = StringBuilder()
    //可变参数解析的时候，我们可以将它当做一个数组，循环遍历取值就行
    for (item in args) {
        tringBuilder.append(item)
        stringBuilder.append(" ")
    }
    return stringBuilder.toString()
  }
  ```
  可变参数调用
  ```
  //传的参数的个数可以是任意个数
  FunctionData().changeParameter("a", "b", "c", "d", "e") 
  ```
## 7、只有单个表达式的方法 
  如果一个函数只有一个一行切有return关键字，那么我们用 = 来代替{}，代码如下：
  ```
  fun sum(x: Int, y: Int) {
     return x + y
  }

  fun sum(x: Int, y: Int) = x + y //这个就是上面那个函数的简写
  ```

# 二、高阶函数用法
## 1、将一个函数作为另一个函数的参数和返回值
   kotiin中的函数是可以可以作为另一个函数的参数和返回值的。这样的函数叫做高阶函数。代码如下：
   ```
   class FunctionData {
      //这个函数将会以参数的形式传到另一个函数中
      fun functionFun(one: String, two: String): String {
          return "${one} ${two}"
      }
   
      //接受一个参数是函数类型的参数：method，返回值也是一个函数
      fun functionFunc(f1: String, f2: String, method: (one: String, two: String) -> String): String {
          return method(f1, f2)
      }
   }
   ```
   上面 ***functionFunc*** 函数的形参method是一个函数，格式为  ***形参名(形参名 : 类型) -> 返回值类型***
   
   接下来将是上面functionFunc函数的具体调用，有三种调用方式，具体代码如下：
   ```
   var functionData = FunctionData()
   
   functionData.functionFunc("第一种写法： ", "用双冒号 :: 表示", functionData::functionFun)
   
   functionData.functionFunc("第二种写法： ","对象直接调用方法",{ x, y -> functionData.functionFun(x, y) })
   
   functionData.functionFunc("第三种写法： ", "用lambda表达式", { x, y -> "${x} ${y}" })
   ```
   第一种采用双冒号(::)表示。 "::"不能直接用在函数或者其他地方调用函数，一般都是将函数作为参数传递的时候使用，使用的格式为  ***类的对象 :: 函数名*** 。当然如果两个方法都是在同一个类中，可以写成
   ***this :: 函数名 ***。this是可以省略的，如***:: 函数名***
   
   另外(::)还可以用在反射中，很方便，代码如下
   ```
   FunctionData::class.simpleName
   FunctionData::class.java
   ```

## 2、let函数
   let函数的作用就是可以避免写一些判断null的操作。当然也可以使得代码更加优雅一点。
   ```
   fun functionLet(){
        var arr = arrayOf("a","b","c",null)
        var type = arr.forEach {
            it?.let {// 加了问号"?"，才会在不为空的情况下执行。不加问号"?"，即使为空，也会执行
                Log.e(TAG,it.toString())
            }
        }
        var dataClass: DataClass? = DataClass("张三", "男", 12)
        var size = dataClass?.let {
            Log.e(TAG, "name = ${it.name};sex = ${it.sex};age = ${it.age}")
            it.name
        }
        Log.e(TAG, size.toString())
   }
   ```
   在let中，it表示其引用的对象，it不可以省略，可以通过it调用引用对象的属性和相关方法。
   返回值为let代码块的最后一行。如果let代码块的最后一行如果有返回值，则返回该值；如果没有，则let代码块没有返回值
    
## 3、with函数
   with函数的作用就是当调用一个对象的多个方法和属性时，可以省去重复的类名。直接调用对象的方法和属性即可。最常用的地方就是RecyclerView中onBinderViewHolder中
   ```
   fun functionWith() {
        var dataClass = DataClass("张三", "男", 12)
        var name = with(dataClass) {
            Log.e(TAG, "name = ${this.name};sex = ${sex};age = ${age}")
            name
        }
        Log.e(TAG, name.toString())
   }
   ```
   with函数是将某对象作为函数的参数，在函数块内可以通过 this 指代该对象,this可以省略不写。
   返回值为with代码块的最后一行。如果with代码块的最后一行如果有返回值，则返回该值；如果没有，则with代码块没有返回值
    
## 4、run函数
   run函数适用于let、with函数的任何场景，可以说run函数其实就是let和with函数的结合体。run函数可以像let函数进行对象空值判断，也可以像with函数那样省略类名
   ```
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
   ``` 
   返回值为run代码块的最后一行。如果run代码块的最后一行如果有返回值，则返回该值；如果没有，则run代码块没有返回值
## 5、apply函数
   apply函数和run函数很像，唯一不同点就是它们各自返回的值不一样，run函数是返回最后一行代码的值，而apply函数的返回的是传入对象的本身。根据这个特性，apply函数一般用于对象初始化的时候，对对象的属性进行赋值。
   同时他可以进行非空判定，可以用于多层级非空判定逻辑
   ```
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
   ```

   
## 6、also函数 
   also和let很像,唯一的区别就是返回值的不一样，also适用于let函数的任何场景。also函数返回的是传入对象的本身,而let函数返回时let代码块的最后一行的值。一般用于多个扩展函数的链式调用
   ```
   fun functionAlso() {
        var dataClass: DataClass? = DataClass("张三", null, 12)
        var alsoObject = dataClass.also {
            it?.sex = "男"
            100//此时返回的不是 100，而是dataClass对象
        }
        Log.e(TAG, alsoObject.toString())
    }
   ```
  
## 7、Filter函数 
   Filter函数，顾名思义就是用来过滤的,Filter代码块里面需要一个Boolean类型的，代码如下
   ```
   fun functionFilter() {

        (0..6).filter { it % 2 == 0 }.forEach{Log.e(TAG, it.toString())}

        //可以是if...else
        (0..6).filter { if(it > 0){return@filter true} else return@filter false }.forEach{Log.e(TAG, it.toString())}
    }
   ```
   上面就是Filter的用法，当然还有几个和Filter类似的函数，如:
   - 1、filterNot和filterNotTo：与filter相反，它们是找出不符合的
   - 2、filterIndexed()和filterIndexedTo()：过滤掉不想要的索引元素
   - 3、filterNotNull()和filterNotNullTo()：过滤掉空的元素
   - 4、filterIsInstance()和filterIsInstanceTo()：筛选指定类型的元素
   
   具体代码如下：
   ```
   arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filterNotTo(mutableListOf<Int>(1, 2, 3)) {it > 0 }.forEach { Log.e(TAG, "filterNotTo : ${it.toString()}") }
   //还可以这么传
   arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filterNot { boo(it) }.forEach { Log.e(TAG, "filterNot : ${it.toString()}") }
   
   arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filterIndexed {index, i -> index % 2 == 0 }.forEach {  Log.e(TAG, "filterIndexed : ${it.toString()}") }
   arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filterIndexedTo(mutableListOf<Int>(1, 2, 3)){index, i -> index % 2 == 0 }.forEach {  Log.e(TAG, "filterIndexedTo : ${it.toString()}") }
   
   arrayOf("a","b","c",null).filterNotNull().forEach { Log.e(TAG, "filterNotNull : ${it.toString()}") }
   arrayOf("a","b","c",null).filterNotNullTo(mutableListOf("a",null)).forEach {  Log.e(TAG, "filterNotNullTo : ${it.toString()}") }
   
   arrayOf("a","b","c",1,2,3).filterIsInstance(String::class.java).forEach { Log.e(TAG, "filterIsInstance : ${it.toString()}") }
   arrayOf("a","b","c",1,2,3).filterIsInstanceTo(mutableListOf("c","d",1,2,3,4),String::class.java).forEach { Log.e(TAG, "filterIsInstanceTo : ${it.toString()}") }
   
   
    private fun boo(it: Int): Boolean {
        return it > 0
    }
   ```
## 8、TakeWhile函数
   takeWhile：当遇到不符合条件的情况，程序终止。比如说，在将（0...10）这个数组里小于5的都取出来
   ```
   (1..10).takeWhile { it < 5 }.forEach { Log.e(TAG, "TakeWhile : ${it.toString()}") }//这个输出1,2,3,4
   
   arrayOf(1,10,2,3,4,5,6,7,8,9).takeWhile { it < 5 }.forEach { Log.e(TAG, "TakeWhile : ${it.toString()}") }//这个只输出1
   ```
   但是要注意的是，使用takeWhile，当第一次遇到不符合条件的情况，就直接终止程序，所以如果上述数组不是顺序的，要取出数组里小于5的数可能会出错
   
   
## 9、Map函数
   map的含义就是映射。将数组里面原有的值，按照一定的表达式转换成另外的值
   ```
   (1..5).map { it * 2 }.forEach { Log.e(TAG, "map : ${it.toString()}") }//将数组里所有的值都乘以2
   ```
   上述的[1,2,3,4,5]数组经过map转换之后，数组里面的元素就会变成[2,4,6,8,10]
   
   
## 9、FlatMap函数
   flatMap函数会把多个数组转换成一个数组。
   ```
   var arr1 = arrayOf(1, 2, 3)
   var arr2 = arrayOf(4, 5, 6)
   var arr3 = arrayListOf(arr1, arr2)
   
   arr3.forEach{Log.e(TAG, "arr3 forEach : ${it.toString()}") }//这里forEach代码块里的it表示arr1和arr2对象的引用
   
   arr3.flatMap { iterator -> iterator.asList()}.forEach { Log.e(TAG, "flatMap : ${it.toString()}") }//这里forEach代码块里的it表示arr1和arr2数组合二为一之后数组里的元素
   ```
   
## 10、Reduce函数
   reduce函数主要是用于集合中元素的计算操作，比如对集合中的元素求和、求阶层等等。可以和map函数一起使用
   ```
   (1..5).map { (1..it).reduce { acc, i -> acc * i } }.forEach { Log.e(TAG, "reduce : ${it.toString()}") }//通过map转换，把集合中的每一个数转换成相应的阶层数
   (1..5).map { (1..it).reduce { acc, i -> i * i } }.forEach { Log.e(TAG, "reduce : ${it.toString()}") }//通过map转换，把集合中的每一个数转换成相应的平方数。但是显然没必要写的这么麻烦，参照map，直接it*it就可以实现
   ```
## 11、Fold函数
   fold函数主要用于累加的操作。比如说字符串的拼接
   ```
    var foldResStr = arrayOf("11", 22, "33").fold(StringBuffer()) { sb, i -> sb.append(i).append(",") }
    Log.e(TAG, "Fold : ${foldResStr.toString()}")//打印结果：11,22,33
   ```
## 12、Use函数
   use函数只能被实现了Closeable的对象使用，它会在程序结束时，自动调用close方法，常用语文件操作
   ```
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
   ```
   
   
   
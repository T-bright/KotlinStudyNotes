# 一、基本用法
先看一个函数的基本申明
- 无输入无输出参数
  ```
   public fun noInputAndNoOutPut_Parameters(): Unit {

   }
  ```
  上面是一个无参无返回值的函数。public表示这个函数是公开的，而Kotlin默认就是public，所以public可以不用写。
   ***Unit*** 表示一个函数没有返回值，类似于java中的void，Kotlin中Unit也可以省略不写。

- 无输入有输出参数
  ```
   public fun noInputAndHaveOutPut_Parameters(): String {
        return "无输入有输出参数"
   }
  ```
  这是一个有返回值的函数，返回值是String类型的。如果返回值是Boolean类型的，把String换成Boolean即可。其他返回值类型，同理。

- 有输入有输出参数
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
   
- 输入的参数可以为空
  
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
  
- 默认参数

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

- 可变参数
  
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
   
# 二、特殊的函数(let、with、run、apply、also)
## 1、let函数
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
    
## 2、with函数
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
    
## 3、run函数
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
## 4、apply函数
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
   
## 5、also函数
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
  
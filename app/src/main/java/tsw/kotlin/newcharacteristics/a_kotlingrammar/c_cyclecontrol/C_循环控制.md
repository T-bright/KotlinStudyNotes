# 一、循环控制语句：for循环、While循环、do...While循环

## 1、for循环
直接上代码
```
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
for ((index,value) in array.withIndex()) {
    println("for循环代码块，直接拿到索引和数组中的元素 ，角标：${index} ；元素：${value}")
}


println()
println("=========================迭代器遍历数组和集合======================")
//var it = list.iterator()
var it = list.iterator()
for (item in it.iterator()) {
    println("for循环代码块，通过索引遍历 ：${item}")
}

println()
println("=========================forEach遍历数组和集合(可以是数组、集合、迭代器)======================")
//array.forEach {
//list.forEach {
it.forEach {
     println("forEach遍历 ：${it}")
}

```

## 2、While循环和do...While循环
```
var index = 0
while (index < 10) {
    index++
}
println("index 第一次循环：${index}")
do {
    index++
} while (index < 10)
println("index 第二次循环：${index}")
        
        
打印结果：
System.out: index 第一次循环：10
System.out: index 第二次循环：11
```
While循环和do...While循环用法差不多。但是他们之间最大的一个区别就是：
While循环先判断然后判断是否进入循环体，
而do...While循环是先进入循环体然后在判断是否满足条件再一次进入循环体

# 二、konlin跳转结构和java差不多，分别是： return , break , continue
return   ：默认从最直接包围它的函数或者匿名函数返回。
break    ：终止最直接包围它的循环。
continue ：继续下一次最直接包围它的循环。
```
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

    //跳到相应的循环体中
    a@ for (i in 1..5) {
        println("a循环")
        b@ for (j in 1..5) {
            println("b循环")
            if (i == 2) {
                continue@a//结束当前的b循环，跳到a循环
            } else if (i == 3) {
                break@a//结束所有循环
            }
        }
    }
    
    index = 0
    for (i in 1..10) {
        if (i == 5) {
            return//结束当前forReturn方法
        }
        index = i
    }
    println("return ：${index}")
}
```
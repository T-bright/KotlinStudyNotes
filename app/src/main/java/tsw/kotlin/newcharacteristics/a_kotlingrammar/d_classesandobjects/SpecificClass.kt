package tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects

//特殊类的
class SpecificClass(var name : String = "我是特殊类") {
    //嵌套类
    class SpecificClassOne {
        //在一个类中定义一个类，如SpecificClassOne，这个类叫着嵌套类
        //嵌套类不能访问外部类的任何属性和方法
        fun getClassName(): String {
            //return name // 编译期无法通过，因为嵌套类无法访问外部类的任何属性和方法
            return "我是嵌套类"
        }
    }

    //内部类
    inner class SpecificClassTwo {
        fun getName(): String {
            return name
        }
    }
}
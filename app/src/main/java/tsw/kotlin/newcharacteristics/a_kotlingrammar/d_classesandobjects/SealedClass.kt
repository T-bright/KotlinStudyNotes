package tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects

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
package tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import tsw.kotlin.newcharacteristics.R

class ClassAndObjectActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_and_object)

        var dClassesAndObjects = D_ClassesAndObjects("张三", "男")
//        dClassesAndObjects.setAddress("上海")
//        println("获取成员属性，name = ${dClassesAndObjects.name}")

//        D_ClassesAndObjects.CompanionObject.isMan(D_ClassesAndObjects.MANFLAG)
//        D_ClassesAndObjects.isMan(D_ClassesAndObjects.MANFLAG)
//        var maleDog = MaleDog("突然跑不动了")
//        maleDog.howToRun()

        //嵌套类的调用
        var specificClassOne = SpecificClass.SpecificClassOne()
        specificClassOne.getClassName()

        //内部类的调用
        var specificClassTwo = SpecificClass("内部类").SpecificClassTwo()
        specificClassTwo.getName()

        //数据类的调用
        var dataClassOne = DataClass("张三", "男", 14)
        var dataClassTwo = dataClassOne.copy()
        var dataClassThree = dataClassOne.copy(name = "李四", sex = "女")
        println(dataClassOne)
        println(dataClassTwo)
        println(dataClassThree)
        println(dataClassOne.equals(dataClassTwo))
        println(dataClassOne.equals(dataClassThree))

        //枚举类
        //ordinal表示枚举类型的序号，name表示枚举类型的名称
        for (i in 0..2) {
            when (i) {//普通枚举类
                EnumClasOne.RED.ordinal -> println(
                    EnumClasOne.RED.name
                )
                EnumClasOne.BLUE.ordinal -> println(
                    EnumClasOne.BLUE.name
                )
                EnumClasOne.BLACK.ordinal -> println(
                    EnumClasOne.BLACK.name
                )
                else -> println("不知道什么鬼颜色")
            }
        }

        for (i in 0..2) {
            when (i) {//普通枚举类
                //不用name，用自定义属性describe
                EnumClasTwo.RED.ordinal -> println(EnumClasTwo.RED.describe)
                EnumClasTwo.BLUE.ordinal -> println(EnumClasTwo.BLUE.describe)
                EnumClasTwo.BLACK.ordinal -> println(EnumClasTwo.BLACK.describe)
                //枚举类虽然也可以添加一个主构造函数，但是这个主构造函数不是给别人用的，是给自己用的。外部类是没法调用枚举类的构造函数
//                else -> EnumClasTwo("什么鬼颜色")
                else -> println("不知道什么鬼颜色")
            }
        }

        //密封类
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

    }


}

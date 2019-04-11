package tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects.extendsexample

import tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects.extendsexample.Animal
import tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects.extendsexample.AnimalBellow
import tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects.extendsexample.AnimalSleep

open class Dog(sex: String,name : String, val run: String) : Animal(sex,name),
    AnimalBellow,
    AnimalSleep {
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
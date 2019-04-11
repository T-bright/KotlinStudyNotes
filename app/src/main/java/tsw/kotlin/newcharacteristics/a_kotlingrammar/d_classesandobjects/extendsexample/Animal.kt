package tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects.extendsexample

abstract class Animal(sex: String, name: String) : AnimalBase(sex) {
    abstract fun getName(): String
}
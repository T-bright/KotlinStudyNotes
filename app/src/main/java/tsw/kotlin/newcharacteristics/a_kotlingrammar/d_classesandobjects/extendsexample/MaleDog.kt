package tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects.extendsexample

import tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects.DataClass

class MaleDog constructor( run: String,sex: String = "公", name: String = "旺财") : Dog(sex, name, run) {
    fun eat() {
        println("${mName}真能吃")
    }

    override fun howToRun() {
        println(run)
    }
}
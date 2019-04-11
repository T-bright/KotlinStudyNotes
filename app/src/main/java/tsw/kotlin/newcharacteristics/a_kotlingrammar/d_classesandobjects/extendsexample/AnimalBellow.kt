package tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects.extendsexample

/**
 *
 */
interface AnimalBellow {
    fun animalVellow(): String;//动物的吼叫声
    fun fly(): String {
        return "只有鸟会飞"
    }
}
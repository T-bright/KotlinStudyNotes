package tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects


/**
 * 类或对象
 */
//类名后面的constructor可以省略，
// 但是如果构造器有注解，或者有可见度修饰符，这时constructor关键字是必须要的，注解和修饰符要放在它之前。
//class D_ClassesAndObjects constructor(name : String ,sex : String){
class D_ClassesAndObjects constructor(var name: String, val sex: String, age: String = "8") {
    var mAddress: String = "";

//    init {
//        println("这是主构造函数的内部方法，name = ${name} ,sex = ${sex},age = ${age}")
//    }
//
//    fun setAddress(address: String) {
//        mAddress = address
//        println("这是setAddress方法，name = ${name} ,mAddress = ${mAddress}")
//    }

    companion object CompanionObject {
        val MANFLAG = 0;
        val MAN = "男"
        val WOMAN = "女"
        val TRANSGENDER = "变性人"
        fun isMan(flag : Int){
            when (flag){
                0 -> println(MAN)
                1 -> println(WOMAN)
                else -> println(TRANSGENDER)
            }

        }
    }
}
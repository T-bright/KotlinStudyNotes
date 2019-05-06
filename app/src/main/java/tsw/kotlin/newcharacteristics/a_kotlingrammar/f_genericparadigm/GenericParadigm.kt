package tsw.kotlin.newcharacteristics.a_kotlingrammar.f_genericparadigm

import android.util.Log

/**
 * 泛型的相关语法
 */
open class GenericParadigm {
    open var value = "Generic_Paradigm"

    class TestOneClass<T>(t: T) {
        var value = t
        fun merge(one: TestOneClass<in T>, two: TestOneClass<T>) {

        }
    }

    class TestTwoClass<T : GenericParadigm>(t: T) {
        var value = t

    }

    open class TestThreeClass : GenericParadigm() {
        override var value = "TestThreeClass"
    }

    class TestFourClass : TestThreeClass() {
        override var value = "TestFourClass"
    }

    //逆变,其或其父类
    class TestFiveClass<in T>(t: T) {
        var value = "TestFiveClass"
        fun getType(t: T) {//逆变可以做入参，不能做返回值

        }
    }

    //协变,其或其子类
    class TestSixClass<out T>(val t: T) {
        var value = "TestSixClass"
        fun getType(): T {//协变不可以做入参，可以用来做返回值
            return t
        }
    }


    fun oneTest() {
        var testOne = TestOneClass<String>("aaaa")
        log(testOne.value)
        testOne = TestOneClass("bbbbb")
        log(testOne.value)

        var ss = TestOneClass<String?>(null)//
        log(ss.value.toString())
    }


    //泛型约束
    fun twoTest() {
        //此时接收null的就会报错，编译无法通过
//        TestTwoClass(null)

        //此时TestTwoClass只能接收GenericParadigm类型和GenericParadigm子类的类型
        var testTwoClass = TestTwoClass<TestThreeClass>(TestThreeClass())
        var testThreeClass = testTwoClass.value
        log(testThreeClass.value)


        //泛型的多个约束
        copy(listOf<String>("1", "5", "6"), "4")

    }

    //对于多个约束上界，可以用where表示。下面的T的上界是CharSequence和Comparable<T>，而String的上界（父类）是CharSequence和Comparable<String>，所以可以穿String类型的
    fun <T> copy(list: List<T>, thresold: T): List<String>
            where  T : CharSequence, T : Comparable<T> {
        return list.filter { it > thresold }.map { it.toString() }
    }

    //型变
    fun threeTest() {
        //逆变类型限定其或其父类
        var genericParadigmNB = TestFiveClass<GenericParadigm>(GenericParadigm())
        var testThreeClassNB = TestFiveClass<TestThreeClass>(TestThreeClass())
        var testFourClassNB = TestFiveClass<TestFourClass>(TestFourClass())
        testThreeClassNB = genericParadigmNB
        //下面这行代码编译不通过，因为逆变类型限定其或其父类，而TestFourClass是TestThreeClass的子类，所以编译不通过
//        testThreeClassNB = testFourClassNB


        //协变类型限定其或其子类
        var genericParadigmXB = TestSixClass<GenericParadigm>(GenericParadigm())
        var testThreeClassXB = TestSixClass<TestThreeClass>(TestThreeClass())
        var testFourClassXB = TestSixClass<TestFourClass>(TestFourClass())
        testThreeClassXB = testFourClassXB
        //下面这行代码编译不通过，因为协变类型限定其或其子类，而GenericParadigm是TestThreeClass的父类，所以编译不通过
//        testThreeClassXB = genericParadigmXB
    }

    //类型投影
    fun fourTest() {
        var parameterStr = TestOneClass<String>("11")
        val parameterAny = TestOneClass<Any>("")
        parameterStr.merge(parameterAny, parameterStr)
    }

    //星号投射
    fun fiveTest() {
        var parameterStr = TestOneClass<String>("11")
        val arrayListAny: ArrayList<Any?> = arrayListOf("a", 1, 'c')
        arrayListAny.add(parameterStr)

        val arrayListX: ArrayList<*> = arrayListOf("a", 1, 'c')
//        arrayListX.add(parameterStr) // 编译器报错

        val arrayListo: ArrayList<out Any?> = arrayListOf("a", 1, 'c')
//        arrayListo.add(parameterStr)  // 编译器报错
    }


    fun log(message: String) {
        Log.e("GenericParadigm", message)
    }
}
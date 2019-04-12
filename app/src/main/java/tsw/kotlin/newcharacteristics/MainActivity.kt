package tsw.kotlin.newcharacteristics

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import tsw.kotlin.newcharacteristics.a_kotlingrammar.a_basic.A_DataType
import tsw.kotlin.newcharacteristics.a_kotlingrammar.b_condition.B_ConditionControl
import tsw.kotlin.newcharacteristics.a_kotlingrammar.c_cyclecontrol.C_CycleControl
import tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects.ClassAndObjectActivity
import tsw.kotlin.newcharacteristics.a_kotlingrammar.e_function.FunctionActivity

//参考：http://www.runoob.com/kotlin/kotlin-basic-types.html
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        a_DataType()
//        b_ConditionControl()
//        c_CyclControl()
    }

    fun gotoClassesAndObject(view: View) {
        var intent = Intent(this, ClassAndObjectActivity::class.java)
        startActivity(intent)
    }

    fun function(view: View) {
        var intent = Intent(this, FunctionActivity::class.java)
        startActivity(intent)
    }


    private fun c_CyclControl() {
        var cCycleControl = C_CycleControl()
        cCycleControl.forCycleControl()
        cCycleControl.whileCycleControl()
        cCycleControl.forReturn()
    }

    fun b_ConditionControl() {
        var bConditionControl = B_ConditionControl()
        bConditionControl.whenConditionControl()
    }

    fun a_DataType() {
        var ADataType: A_DataType =
            A_DataType()
        ADataType.printBasicDataType()
        ADataType.array()
        ADataType.stringOperation()
        ADataType.isNull()
        ADataType.isClassCastException()
    }


}

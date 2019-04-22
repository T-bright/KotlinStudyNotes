package tsw.kotlin.newcharacteristics

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import tsw.kotlin.newcharacteristics.a_kotlingrammar.a_basic.DataTypeActivity
import tsw.kotlin.newcharacteristics.a_kotlingrammar.b_condition.ConditionControlActivity
import tsw.kotlin.newcharacteristics.a_kotlingrammar.c_cyclecontrol.CycleControlActivity
import tsw.kotlin.newcharacteristics.a_kotlingrammar.d_classesandobjects.ClassAndObjectActivity
import tsw.kotlin.newcharacteristics.a_kotlingrammar.e_function.FunctionActivity

//参考：http://www.runoob.com/kotlin/kotlin-basic-types.html
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    private fun initListener() {
        basic.setOnClickListener { startActivity(Intent(this, DataTypeActivity::class.java)) }//基础语法

        condition_control.setOnClickListener { startActivity(Intent(this, ConditionControlActivity::class.java)) }//条件空值语句

        cycle_control.setOnClickListener { startActivity(Intent(this, CycleControlActivity::class.java)) }//循环

        classes_object.setOnClickListener { startActivity(Intent(this, ClassAndObjectActivity::class.java)) }//类和对象

        function.setOnClickListener { startActivity(Intent(this, FunctionActivity::class.java)) }//函数
    }

}

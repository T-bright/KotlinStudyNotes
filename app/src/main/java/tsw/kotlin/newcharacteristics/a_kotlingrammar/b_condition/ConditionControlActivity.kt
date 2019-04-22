package tsw.kotlin.newcharacteristics.a_kotlingrammar.b_condition

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_condition_control.*
import tsw.kotlin.newcharacteristics.R

class ConditionControlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_condition_control)
        var cc = B_ConditionControl()
        if_else_condition.setOnClickListener { cc.ifElseConditionControl() }

        when_condition.setOnClickListener { cc.whenConditionControl() }
    }
}

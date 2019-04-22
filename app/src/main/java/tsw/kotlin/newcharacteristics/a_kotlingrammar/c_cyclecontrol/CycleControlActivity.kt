package tsw.kotlin.newcharacteristics.a_kotlingrammar.c_cyclecontrol

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cycle_control.*
import tsw.kotlin.newcharacteristics.R

class CycleControlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cycle_control)
        var cc = C_CycleControl()
        for_condition.setOnClickListener { cc.forCycleControl() }

        return_condition.setOnClickListener { cc.forReturn() }

        while_condition.setOnClickListener { cc.whileCycleControl() }
    }
}

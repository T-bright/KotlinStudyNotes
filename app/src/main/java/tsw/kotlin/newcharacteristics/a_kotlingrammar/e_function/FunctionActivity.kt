package tsw.kotlin.newcharacteristics.a_kotlingrammar.e_function

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_function.*
import tsw.kotlin.newcharacteristics.R

/**
 * 这里展示kotlin的基本用法和一些特殊的内联函数
 */
class FunctionActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var functionData: FunctionData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function)
        functionData = FunctionData()
        one.setOnClickListener(this)
        two.setOnClickListener(this)
        three.setOnClickListener(this)
        four.setOnClickListener(this)
        five.setOnClickListener(this)
    }

    var index = 0
    var flag = true
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.one -> {
                show.text = "无输入无输出参数"
                functionData.noInputAndNoOutPut_Parameters()
            }
            R.id.two -> {
                show.text = functionData.noInputAndHaveOutPut_Parameters()
            }
            R.id.three -> {
                flag = !flag
                if (flag) show.text = functionData.hvaeInputAndHaveOutPut_Parameters("不能为空的输入参数") else show.text =
                    functionData.parameterCanBeNull("可以为空的输入参数")
            }
            R.id.four -> {
                four()
            }
            R.id.five -> {
//                show.text = functionData.changeParameter("a", "b", "c", "d", "e")
                show.text = functionData.changeParameter2(arrayOf("a","b","c","d","e"),arrayOf("f","g","h","i","j"))
            }
        }
    }

    private fun four() {
        when (index) {
            0 -> {
                show.text = functionData.defaultParameter()
                index = 1
            }
            1 -> {
                show.text = functionData.defaultParameter("不是默认参数one")
                index = 2
            }
            2 -> {
                show.text = functionData.defaultParameter(two = "不是默认参数two")
                index = 3
            }

        }

    }


}

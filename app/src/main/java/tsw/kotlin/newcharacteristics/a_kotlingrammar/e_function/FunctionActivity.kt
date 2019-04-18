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
        six.setOnClickListener(this)
        serven.setOnClickListener(this)
        eight.setOnClickListener(this)

        nine.setOnClickListener(this)
        ten.setOnClickListener(this)
        eleven.setOnClickListener(this)
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
                flag = !flag
                if (flag) show.text = functionData.changeParameter("a", "b", "c", "d", "e") else
                    show.text = functionData.changeParameter2(
                        arrayOf("a", "b", "c", "d", "e"),
                        arrayOf("f", "g", "h", "i", "j")
                    )
            }
            R.id.six -> {
                functionData.functionLet()
                show.text = "let函数"
            }
            R.id.serven -> {
                functionData.functionWith()
                show.text = "with函数"
            }
            R.id.eight -> {
                functionData.functionRun()
                show.text = "run函数"
            }
            R.id.nine -> {
                functionData.functionApply()
                show.text = "apply函数"
            }
            R.id.ten -> {
                functionData.functionAlso()
                show.text = "also函数"
            }
            R.id.eleven -> {
                eleven()
            }
            R.id.twelve ->{

            }
            R.id.thirteen ->{

            }
            R.id.fourteen ->{

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
                index = 0
            }

        }

    }

    private fun eleven() {
        when (index) {
            0 -> {
                show.text = functionData.functionFunc("第一种写法： ", "用双冒号 :: 表示", functionData::functionFun)
                index = 1
            }
            1 -> {
                show.text = functionData.functionFunc("第二种写法： ","对象直接调用方法",{ x, y -> functionData.functionFun(x, y) })
                index = 2
            }
            2 -> {
                show.text = functionData.functionFunc("第三种写法： ", "用lambda表达式", { x, y -> "${x} ${y}" })
                index = 0
            }

        }

    }


}

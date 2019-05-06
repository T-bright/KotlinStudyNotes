package tsw.kotlin.newcharacteristics.a_kotlingrammar.f_genericparadigm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_generic_paradigm.*
import tsw.kotlin.newcharacteristics.R

class GenericParadigmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val genericParadigm = GenericParadigm()
        setContentView(R.layout.activity_generic_paradigm)
        onetest.setOnClickListener {
            genericParadigm.oneTest()
            show("泛型的写法")
        }

        twotest.setOnClickListener {
            genericParadigm.twoTest()
            show("泛型的约束")
        }

        threetest.setOnClickListener {
            genericParadigm.threeTest()
            show("泛型的声明处型变")
        }

        fourtest.setOnClickListener {
            genericParadigm.fourTest()
            show("泛型的类型投影")
        }

        fivetest.setOnClickListener {
            genericParadigm.fiveTest()
            show("泛型的星号投射")
        }

        sixtest.setOnClickListener {
            genericParadigm.sixTest()
            show("反射")
        }
    }

    fun show(text: CharSequence) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }
}

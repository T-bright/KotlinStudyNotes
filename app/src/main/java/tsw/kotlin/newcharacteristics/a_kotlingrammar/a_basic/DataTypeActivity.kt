package tsw.kotlin.newcharacteristics.a_kotlingrammar.a_basic

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_data_type.*
import tsw.kotlin.newcharacteristics.R

class DataTypeActivity : AppCompatActivity() {
    lateinit var aDataType: A_DataType
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_type)
        aDataType = A_DataType()
        basic_data_type.setOnClickListener { aDataType.printBasicDataType() }//基本数据类型

        array_data_type.setOnClickListener { aDataType.array() }//数组类型

        char_data_type.setOnClickListener { aDataType.char('a') }//字符类型

        string_data_type.setOnClickListener { aDataType.stringOperation() }//字符串操作和字符串模板操作

        null_data_type.setOnClickListener { aDataType.isNull() }//空判断

        classCast_data_type.setOnClickListener { aDataType.isClassCastException() }//安全的类型转换
    }
}

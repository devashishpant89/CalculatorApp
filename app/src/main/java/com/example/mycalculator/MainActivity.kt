package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var lastNumeric = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view: View){
        tvInput.append((view as Button ).text)
        lastNumeric=true
    }
    fun onClear(view: View){
        tvInput.text= ""
        lastNumeric=false
        lastDot=false
    }
    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvInput.append(".")
            lastNumeric= false
            lastDot= true
        }
    }
    private fun removeZeroAfterDot(result: String):String{
        var value= result
        if(result.contains(".0"))
            value = result.substring(0,result.length-2)
            // suppose we have 85.0 so it will remove last 2 characters
            //                [0123] so it will remove .0
            return value
    }

    fun onOperator(view: View){
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric=false
            lastDot=false
        }
    }
    fun onEqual(view: View){
    if(lastNumeric){
        var tvValue= tvInput.text.toString()
        var prefix= ""

        try { if (tvValue.startsWith("-")) {
                prefix = "-"
                tvValue = tvValue.substring(1)
            }
            if(tvValue.contains("-")) {
            val splitValue = tvValue.split("-")
            // suppose example expression = 99-1
            var one = splitValue[0] // 99 wala idhar store hoga
            var two = splitValue[1] // 1 wala idhar store hoga
                if (!prefix.isEmpty()){
                    one= prefix + one
                }
                tvInput.text= removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
             }
            else if(tvValue.contains("+")) {
                val splitValue = tvValue.split("+")
                // suppose example expression = 99+1
                var one = splitValue[0] // 99 wala idhar store hoga
                var two = splitValue[1] // 1 wala idhar store hoga
                if (!prefix.isEmpty()){
                    one= prefix + one
                }
                tvInput.text= removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
            }
            else if(tvValue.contains("/")) {
                val splitValue = tvValue.split("/")
                // suppose example expression = 100/2
                var one = splitValue[0] // 100 wala idhar store hoga
                var two = splitValue[1] // 2 wala idhar store hoga
                if (!prefix.isEmpty()){
                    one= prefix + one
                }
                tvInput.text= removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
            }
            else if(tvValue.contains("X")) {
                val splitValue = tvValue.split("X")
                // suppose example expression = 20*3
                var one = splitValue[0] // 20 wala idhar store hoga
                var two = splitValue[1] // 3 wala idhar store hoga
                if (!prefix.isEmpty()){
                    one= prefix + one
                }
                tvInput.text= removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
            }


        }catch (e: ArithmeticException){
         e.printStackTrace()
        }
    }
    }

    private fun isOperatorAdded(value: String) : Boolean{
        return if(value.startsWith(prefix = "-")){
            false }
        else {
            value.contains("/") || value.contains("*")
                    || value.contains("-") || value.contains("+")
        }
    }
}
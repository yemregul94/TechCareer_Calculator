package com.works.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.works.calculator.databinding.ActivityMainBinding
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var numberOnScreen = "0"
    var numberHidden = "0"
    var isResult = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            button0.setOnClickListener { numberPressed(button0.text.toString()) }
            button1.setOnClickListener { numberPressed(button1.text.toString()) }
            button2.setOnClickListener { numberPressed(button2.text.toString()) }
            button3.setOnClickListener { numberPressed(button3.text.toString()) }
            button4.setOnClickListener { numberPressed(button4.text.toString()) }
            button5.setOnClickListener { numberPressed(button5.text.toString()) }
            button6.setOnClickListener { numberPressed(button6.text.toString()) }
            button7.setOnClickListener { numberPressed(button7.text.toString()) }
            button8.setOnClickListener { numberPressed(button8.text.toString()) }
            button9.setOnClickListener { numberPressed(button9.text.toString()) }

            buttonAdd.setOnClickListener { addition() }
            buttonEqual.setOnClickListener { result() }
            buttonClear.setOnClickListener { clear() }
            buttonRemove.setOnClickListener { remove() }

            //Daha sonra eklenecek özellikler
            buttonSubtract.setOnClickListener {  }
            buttonMultiply.setOnClickListener {  }
            buttonDivide.setOnClickListener {  }
        }
    }

    fun numberPressed(number: String){
        if(isResult){
            isResult = false    //Eğer ekranda sonuç varsa ve tekrar bir sayıya basılırsa temizleme işlemi yapılır.
            clear()             //Yorum satırlarındaki tüm ihtimaller için Windows 10 hesap makinesi baz alındı
        }

        numberOnScreen = binding.textViewScreen.text.toString()

        if (numberOnScreen == "0"){
            numberOnScreen = number
        } else {
            numberOnScreen += number
        }

        binding.textViewScreen.text = numberOnScreen
    }

    fun addition(){
        if (numberHidden != "0"){
            if(!isResult){
                result()    //Eğer 2 sayı da girildiyse ve sonuç gelmediyse operatörlere tekrar tıklamak sonucu verir
            }
        } else {
            numberHidden = numberOnScreen
            binding.textViewScreen.text = "0"
            binding.textViewHistory.text = "$numberHidden + "
        }
    }

    fun result(){
        isResult = true
        val result = numberHidden.toInt() + numberOnScreen.toInt()
        binding.textViewScreen.text = result.toString()
        binding.textViewHistory.text = "$numberHidden + $numberOnScreen = "

        numberHidden = binding.textViewScreen.text.toString()   //Sonuç geldikten sonra tekrar eşittir tuşuna basılırsa son işlem tekrarlanır
                                                                 //eğer tek sayı girilmişse diğer sayı da o girilen sayıya eşitlenir
    }

    fun clear(){
        numberOnScreen = "0"
        numberHidden = "0"
        binding.textViewScreen.text = "0"
        binding.textViewHistory.text = ""
    }

    fun remove(){
        if(!isResult){
            numberOnScreen = numberOnScreen.dropLast(1)
            if(numberOnScreen == "") {numberOnScreen = "0"}
            binding.textViewScreen.text = numberOnScreen

        }
    }
}
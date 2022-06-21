package com.example.mental_health_app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.example.mental_health_app.databinding.ActivityQuotesBinding

class QuotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuotesBinding.inflate(layoutInflater)
        binding.quotes.text =quotes()
        binding.author.text =author()


       // val random :Array<Array<Any>> =random()

       // binding.info.text = random[0][9].toString()
        binding.info.text =info()
        val view: View = binding.root
        setContentView(view)
    }



    private fun quotes(): String {

        val python = Python.getInstance()
        val pythonFile = python.getModule("MQ")
        return pythonFile.callAttr("quotes").toString()
    }
    private fun author(): String {

        val python = Python.getInstance()
        val pythonFile = python.getModule("MQ")
        return pythonFile.callAttr("author").toString()
    }
    private fun info(): String {

        val python = Python.getInstance()
        val pythonFile = python.getModule("Did_You_Know")

        return pythonFile.callAttr("information").toString()
    }

    private fun random(): Array<Array<Any>> {

        val python = Python.getInstance()
        val pythonFile = python.getModule("functions")
        return pythonFile.callAttr("Best_recommendations").toJava(Array<Array<Any>>::class.java)
        //return pythonFile.callAttr("mai2n",2).toJava(ArrayList<String>)

    }






}
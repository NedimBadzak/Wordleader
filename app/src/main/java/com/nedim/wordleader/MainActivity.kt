package com.nedim.wordleader

import android.graphics.Color.green
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.wordleader.R
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    lateinit var prvoSlovo : EditText
    lateinit var prviButton : Button
    lateinit var drugoSlovo : EditText
    lateinit var drugiButton : Button
    lateinit var treceSlovo : EditText
    lateinit var treciButton : Button
    lateinit var cetvrtoSlovo : EditText
    lateinit var cetvrtiButton : Button
    lateinit var petoSlovo : EditText
    lateinit var petiButton : Button

    lateinit var nadjiButton : Button
    lateinit var resultTextView : TextView

    //0 = nije slovo
    //1 = negdje drugo slovo
    //2 = tu slovo
    val boje : Array<Int> = arrayOf(0,0,0,0,0)
    val slova : Array<Char?> = arrayOf(null, null, null, null, null)
    val garantNisuSlova : MutableList<Char> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prvoSlovo = findViewById(R.id.prvoSlovo)
        prviButton = findViewById(R.id.prviButton)
        drugoSlovo = findViewById(R.id.drugoSlovo)
        drugiButton = findViewById(R.id.drugiButton)
        treceSlovo = findViewById(R.id.treceSlovo)
        treciButton = findViewById(R.id.treciButton)
        cetvrtoSlovo = findViewById(R.id.cetvrtoSlovo)
        cetvrtiButton = findViewById(R.id.cetvrtiButton)
        petoSlovo = findViewById(R.id.petoSlovo)
        petiButton = findViewById(R.id.petiButton)

        nadjiButton = findViewById(R.id.button2)
        resultTextView = findViewById(R.id.resultTextView)

        prviButton.setOnClickListener {
            if(prvoSlovo.text.toString().isNotEmpty()) {
               promijeniBoju(boje, 1)
            }
        }
        drugiButton.setOnClickListener {
            if(drugoSlovo.text.toString().isNotEmpty()) {
                promijeniBoju(boje, 2)
            }
        }
        treciButton.setOnClickListener {
            if(treceSlovo.text.toString().isNotEmpty()) {
                promijeniBoju(boje, 3)
            }
        }
        cetvrtiButton.setOnClickListener {
            if(cetvrtoSlovo.text.toString().isNotEmpty()) {
                promijeniBoju(boje, 4)
            }
        }
        petiButton.setOnClickListener {
            if(petoSlovo.text.toString().isNotEmpty()) {
                promijeniBoju(boje, 5)
            }
        }

        nadjiButton.setOnClickListener {
            garantNisuSlova.clear()
            if(prvoSlovo.text.toString().isNotEmpty()) {
                if(boje[0] != 0) slova[0] = prvoSlovo.text.toString().uppercase()[0]
                else garantNisuSlova.add(prvoSlovo.text.toString().uppercase()[0])
            } else {
                slova[0] = null
            }
            if (drugoSlovo.text.toString().isNotEmpty()) {
                if(boje[1] != 0) slova[1] = drugoSlovo.text.toString().uppercase()[0]
                else garantNisuSlova.add(drugoSlovo.text.toString().uppercase()[0])
            } else {
                slova[1] = null
            }
            if (treceSlovo.text.toString().isNotEmpty()) {
                if(boje[2] != 0) slova[2] = treceSlovo.text.toString().uppercase()[0]
                else garantNisuSlova.add(treceSlovo.text.toString().uppercase()[0])
            } else {
                slova[2] = null
            }
            if (cetvrtoSlovo.text.toString().isNotEmpty()) {
                if(boje[3] != 0) slova[3] = cetvrtoSlovo.text.toString().uppercase()[0]
                else garantNisuSlova.add(cetvrtoSlovo.text.toString().uppercase()[0])
            } else {
                slova[3] = null
            }
            if (petoSlovo.text.toString().isNotEmpty()) {
                if(boje[4] != 0) slova[4] = petoSlovo.text.toString().uppercase()[0]
                else garantNisuSlova.add(petoSlovo.text.toString().uppercase()[0])
            } else {
                slova[4] = null
            }
            Log.d("TAGIC", slova.toList().toString())
            Log.d("TAGIC", boje.toList().toString())
            var rezultat : MutableList<String> = mutableListOf()
            val listaRijeci = readFile("lista.txt")
            for(rijec in listaRijeci) {
                var brojac = 0
                for(i in slova.indices) {
                    var slovo: Char?
                    if(slova[i] != null) {
                        slovo = slova[i]
                    } else continue
                    if(boje[i] == 1 && rijec.contains(slovo!!)) {
                        brojac++
                    } else if (boje[i] == 2 && rijec.contains(slovo!!) && rijec[i] == slovo) {
                        brojac++
                    }
                }
                if(brojac == slova.count { it != null }) {
                    Log.d("TAGIC", garantNisuSlova.toList().toString())
                    if(garantNisuSlova.count { rijec.contains(it) } == 0) rezultat.add(rijec)
                }
            }
            resultTextView.text = rezultat.joinToString("\n")
        }
    }


    fun promijeniBoju(boje : Array<Int>, id: Int) : Unit {
        boje[id-1] = if (boje[id-1] < 2) boje[id-1] + 1  else 0
        when (id-1) {
            0 -> {
                prviButton.setBackgroundColor(
                    when (boje[id-1]) {
                        0 -> getColor(R.color.black)
                        1 -> getColor(R.color.gray)
                        else -> getColor(R.color.teal_200)
                    }
                )
            }
            1 -> {
                drugiButton.setBackgroundColor(
                    when (boje[id-1]) {
                        0 -> getColor(R.color.black)
                        1 -> getColor(R.color.gray)
                        else -> getColor(R.color.teal_200)
                    }
                )
            }
            2 -> {
                treciButton.setBackgroundColor(
                    when (boje[id-1]) {
                        0 -> getColor(R.color.black)
                        1 -> getColor(R.color.gray)
                        else -> getColor(R.color.teal_200)
                    }
                )
            }
            3 -> {
                cetvrtiButton.setBackgroundColor(
                    when (boje[id-1]) {
                        0 -> getColor(R.color.black)
                        1 -> getColor(R.color.gray)
                        else -> getColor(R.color.teal_200)
                    }
                )
            }
            4 -> {
                petiButton.setBackgroundColor(
                    when (boje[id-1]) {
                        0 -> getColor(R.color.black)
                        1 -> getColor(R.color.gray)
                        else -> getColor(R.color.teal_200)
                    }
                )
            }
        }
    }

    fun readFile(name: String): List<String> {
        val reader = BufferedReader(InputStreamReader(baseContext.assets.open(name)))

        var line = reader.readLine()

        val list: MutableList<String> = mutableListOf()
        while (line != null) {
            list.add(line.uppercase())
            line = reader.readLine()
        }
        return list
    }
}
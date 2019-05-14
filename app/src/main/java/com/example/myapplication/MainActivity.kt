package com.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val regex = Regex("^\\d\\d\\.\\d\\d\\.\\d\\d\\d\\d\$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tw = object : TextWatcher {
            var ignore = false
            var mask = "________"
            var oldValue = ""
            var newValue = ""
            var start = 0
            var count = 0
            var after = 0
            var reg = Regex("\\_*\\.*\\_*\\.*\\_*")

            override fun afterTextChanged(s: Editable?) {
                Log.d("AFTER", s.toString().replace(reg, ""))
                if(ignore){
                    ignore = false
                    return
                }
                val clean = s.toString().replace(reg, "")
                if (clean.length < mask.length) {
                    var submask = mask.substring(clean.length, mask.length)
                    Log.d("AFTER2", submask)
                    var resultBuild = StringBuilder();
                    for (i in 0..mask.length-1) {
                        if (i < clean.length) {
                            resultBuild.append(clean[i])
                        } else {
                            resultBuild.append(mask[i])
                        }
                        when (i) {
                            1,
                            3 -> {
                                resultBuild.append('.')
                            }
                        }

                    }
                    ignore = true
                    edit.setText(resultBuild.toString())
                    edit.setSelection(start+count)
                    Log.d("AFTER3", resultBuild.toString())
                }
//                )
//                if (!s.toString().equals(current)) {
//
//                    var clean = s.toString().replace("[^\\d.]|\\.", "")
//                    val cleanC = current.replace("[^\\d.]|\\.", "")
//
//                    val cl = clean.length
//                    var sel = cl
//                    var i = 2
//                    while (i <= cl && i < 6) {
//                        sel++
//                        i += 2
//                    }
//                    //Fix for pressing delete next to a forward slash
//                    if (clean == cleanC) sel--
//
//                    if (clean.length < 8) {
//                        clean = clean + ddmmyyyy.substring(clean.length)
//                    } else {
//                        //This part makes sure that when we finish entering numbers
//                        //the date is correct, fixing it otherwise
//                        var day = Integer.parseInt(clean.substring(0, 2))
//                        var mon = Integer.parseInt(clean.substring(2, 4))
//                        var year = Integer.parseInt(clean.substring(4, 8))
//
//                        mon = if (mon < 1) 1 else if (mon > 12) 12 else mon
//                        cal.set(Calendar.MONTH, mon - 1)
//                        year = if (year < 1900) 1900 else if (year > 2100) 2100 else year
//                        cal.set(Calendar.YEAR, year)
//                        // ^ first set year for the line below to work correctly
//                        //with leap years - otherwise, date e.g. 29/02/2012
//                        //would be automatically corrected to 28/02/2012
//
//                        day =
//                            if (day > cal.getActualMaximum(Calendar.DATE)) cal.getActualMaximum(Calendar.DATE) else day
//                        clean = String.format("%02d%02d%02d", day, mon, year)
//                    }
//
//                    clean = String.format(
//                        "%s/%s/%s", clean.substring(0, 2),
//                        clean.substring(2, 4),
//                        clean.substring(4, 8)
//                    )
//
//                    sel = if (sel < 0) 0 else sel
//                    current = clean
//                    edit.setText(current)
//                    edit.setSelection(if (sel < current.length) sel else current.length)

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                this.start = start
                this.count = count
            }

        }
        edit.addTextChangedListener(tw)
    }
}

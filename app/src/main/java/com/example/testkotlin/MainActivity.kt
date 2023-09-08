package com.example.testkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = readFromAsset();

        val adapter = MyAdapter(model, this)

        rcv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rcv.adapter = adapter;
    }


    private fun readFromAsset(): List<Model> {

        val modeList = mutableListOf<Model>()

        val bufferReader = application.assets.open("data.json").bufferedReader()
        val json_string = bufferReader.use {
            it.readText()
        }

        val jsonArray = JSONArray(json_string);
        for (i in 0..jsonArray.length() - 1) {
            val jsonObject: JSONObject = jsonArray.getJSONObject(i)
            val model = Model(jsonObject.getString("name"), jsonObject.getString("description"))
            modeList.add(model)
        }
        return modeList
    }
}
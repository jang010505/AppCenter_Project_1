package com.example.appcenter_project_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.appcenter_project_1.model.Configs
import com.example.appcenter_project_1.model.PapagoEntity
import com.example.appcenter_project_1.model.PapagoServiceCreator
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val LOG_TAG = "MainActivity Request"

    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById<TextView>(R.id.text)

        findViewById<Button>(R.id.btn).setOnClickListener{
            val inputText = findViewById<EditText>(R.id.input)
            if(inputText.text.toString().isNotEmpty()){
                text.text = "잠시만 기달려주세요!"
                val papagoService = PapagoServiceCreator().create()
                val call = papagoService.requestTranslation(query = inputText.text.toString())
                call.enqueue(ExClass())
            }
            else{
                text.text = "문장을 입력해주세요!"
            }
        }
    }

    inner class ExClass : retrofit2.Callback<PapagoEntity> {
        override fun onResponse(call: Call<PapagoEntity>, response: Response<PapagoEntity>) {
            if(response.isSuccessful) {
                Log.d(LOG_TAG, "Successful!")

                val result = response.body()

                text.text = result?.langCode

                Log.e(LOG_TAG, response.raw().toString());
            }
            else {
                Log.e(LOG_TAG, "fail!")
                Log.e(LOG_TAG, "error code : " + response.code())
                Log.e(LOG_TAG, "error message : " + response.message())
            }
        }
        override fun onFailure(call: Call<PapagoEntity>, t: Throwable) {
            Log.d(LOG_TAG, "onFailure!")
            t.printStackTrace()
        }
    }
}
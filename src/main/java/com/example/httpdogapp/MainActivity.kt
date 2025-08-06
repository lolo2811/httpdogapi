package com.example.httpdogapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private val api = RetrofitClient.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.dogImage)
        val btnGet = findViewById<Button>(R.id.btnGet)
        val btnPost = findViewById<Button>(R.id.btnPost)
        val btnPatch = findViewById<Button>(R.id.btnPatch)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        btnGet.setOnClickListener {
            fetchImage(api.getDog("200"))
        }

        btnPost.setOnClickListener {
            fetchImage(api.postDog())
        }

        btnPatch.setOnClickListener {
            fetchImage(api.patchDog())
        }

        btnDelete.setOnClickListener {
            fetchImage(api.deleteDog())
        }
    }

    private fun fetchImage(call: Call<ResponseBody>) {
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val imageUrl = response.raw().request.url.toString()
                Picasso.get().load(imageUrl).into(imageView)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
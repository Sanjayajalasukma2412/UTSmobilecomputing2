package com.example.contactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_image_url.*

class ImageUrlActivity : AppCompatActivity() {
    val url = "https://aws.random.cat/meow"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_url)
        setUpView()
    }

    private fun setUpView(){
        val que = Volley.newRequestQueue(this)
        val req = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
            Toast.makeText(this, it["file"].toString(), Toast.LENGTH_LONG).show()
            Glide.with(this)
                .load(it["file"].toString())
                .into(img_dog)
        }, Response.ErrorListener {
            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
        })

        que.add(req)
    }
}

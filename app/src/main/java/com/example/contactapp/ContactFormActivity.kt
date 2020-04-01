package com.example.contactapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_contact_form.*
import kotlinx.android.synthetic.main.activity_contact_form.toolbar
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.lang.Exception


class ContactFormActivity : AppCompatActivity() {
    val REQUEST_CODE = 100
    var isEdit: Boolean? = false
    var url = "https://api.myjson.com/bins/lzcn8"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_form)


        img_contact.setOnClickListener {
            openGalleryForImage()

        }
        checkCondition()
        saveContact()
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            img_contact.setImageURI(data?.data)
        }
    }


    private fun checkCondition() {

        val user: UserData? = intent.getParcelableExtra(ContactFormActivity::class.java.simpleName)
        if (user != null) {
            isEdit = true
            toolbar.title = "Contact Edit"
            btn_save.text = "Update"
            et_first_name.setText(user.first)
            et_last_name.setText(user.last)
            et_age.setText("${user.age}")
            Glide.with(this)
                .load(user.url)
                .into(img_contact)
        }
    }


    private fun saveContact() {
        //val sharedPref = applicationContext?.getSharedPreferences("Data", Context.MODE_PRIVATE) ?: return
        btn_save.setOnClickListener {
            Toast.makeText(
                this,
                if (isEdit!!) "Data berhasil diubah" else "Data berhasil disimpan",
                Toast.LENGTH_LONG
            ).show()
            if (isEdit!!) {
                putData()
            } else {
                postData()
            }
            finish()
        }
    }

    private fun postData(){
        val params = HashMap<String,String>()
        params["foo1"] = "bar1"
        params["foo2"] = "bar2"
        val jsonObject = JSONObject(params as Map<String, String>)
        val que = Volley.newRequestQueue(this)
        val req = JsonObjectRequest(Request.Method.POST, url, jsonObject, Response.Listener {

        }, Response.ErrorListener {
            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
        })

        que.add(req)
    }

    private fun putData(){
        val params = HashMap<String,String>()
        params["foo1"] = "bar1"
        params["foo2"] = "bar2"
        val jsonObject = JSONObject(params as Map<String, String>)
        val que = Volley.newRequestQueue(this)
        val req = JsonObjectRequest(Request.Method.PUT, url, jsonObject, Response.Listener {

        }, Response.ErrorListener {
            Log.d("error", it.message)
            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
        })

        que.add(req)
    }
}

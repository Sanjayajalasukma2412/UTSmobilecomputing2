package com.example.contactapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private var PRIVATE_MODE = 0
    private val PREF_NAME = "contactApp"
    private val PREF_IS_LOGIN = "isLogin"
    private val PREF_NAME_PROFILE = "name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        if (sharedPref.getBoolean(PREF_IS_LOGIN, false)){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        btn_save.setOnClickListener {
            if (et_name.text.toString() != "") {
                sharedPref.edit().apply{
                    putString(PREF_NAME_PROFILE, et_name.text.toString())
                    putBoolean(PREF_IS_LOGIN, true)
                    apply()
                }
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}

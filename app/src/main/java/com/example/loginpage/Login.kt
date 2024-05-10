package com.example.loginpage

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginpage.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
        lateinit var binding : ActivityLoginBinding
        lateinit var sharedPreferences : SharedPreferences
        var username : String? = null
        var usernameCheck : String? = null
        var password : String? = null
        var passwordCheck : String? = null
        var remember : Boolean = false
//        var rememberMe : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        enableEdgeToEdge()

        username = binding.username.text.toString()
        password = binding.password.text.toString()
        remember = binding.remember.isChecked


        setContentView(R.layout.activity_login)
        binding.reg.setOnClickListener{

            sharedPreferences = getSharedPreferences("Userdetails", MODE_PRIVATE)
            var admin = sharedPreferences.edit()
            admin.putString("username",username)
            admin.putString("password",password)
            admin.putBoolean("remember",false)
            admin.apply()

            Toast.makeText(this,"Registered Successfully",Toast.LENGTH_LONG).show()
        }
        binding.login.setOnClickListener{
            sharedPreferences = getSharedPreferences("Userdetails", MODE_PRIVATE)
            remember = sharedPreferences.getBoolean("remember",false)
            usernameCheck = sharedPreferences.getString("username", null)
            passwordCheck = sharedPreferences.getString("password",null)
            if(usernameCheck == username && passwordCheck == password){
                var intent = Intent(this@Login,DashboardActivity::class.java)
                Toast.makeText(this,"Successfully logged in",Toast.LENGTH_LONG).show()
                startActivity(intent)
            }else{
                Toast.makeText(this,"ERRORRRR",Toast.LENGTH_LONG).show()
            }

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
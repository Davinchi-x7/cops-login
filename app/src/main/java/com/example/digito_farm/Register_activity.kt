package com.example.digito_farm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Register_activity : AppCompatActivity() {
    lateinit var log:Button
    lateinit var user:EditText
    lateinit var Email:EditText
    lateinit var Password:EditText
    lateinit var Create:Button
    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        log = findViewById(R.id.btn_log)
        user = findViewById(R.id.edt_username)
        Email = findViewById(R.id.edt_email)
        Password = findViewById(R.id.edt_password)
        Create = findViewById(R.id.edt_create_user)

        auth = FirebaseAuth.getInstance()

        log.setOnClickListener(){
            var goToLog = Intent(this, MainActivity::class.java)
            startActivity(goToLog)
        }
        Create.setOnClickListener(){
            var user = user.text.toString().trim()
            var mail = Email.text.toString().trim()
            var pass = Password.text.toString().trim()
            if (user.isEmpty() || mail.isEmpty() || pass.isEmpty()){
                Toast.makeText(this, "cannot submit empty fields", Toast.LENGTH_SHORT).show()
            }else{
                auth.createUserWithEmailAndPassword(mail,pass) .addOnCompleteListener(this){
                    if (it.isSuccessful){
                        Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show()
                        val proceed = Intent(this,MainActivity::class.java)
                        startActivity(proceed)
                        finish()
                    }else{
                        Toast.makeText(this, "Failed to create user", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}
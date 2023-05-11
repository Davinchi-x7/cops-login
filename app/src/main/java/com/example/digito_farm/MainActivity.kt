package com.example.digito_farm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var Btn_reg:Button
    lateinit var Email:EditText
    lateinit var Password:EditText
    lateinit var Login:Button
    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Btn_reg = findViewById(R.id.btn_reg)
        Email = findViewById(R.id.login_email)
        Password = findViewById(R.id.Login_password)
        Login = findViewById(R.id.Loginpg_login_btn)
        auth = FirebaseAuth.getInstance()


        Login.setOnClickListener(){
            var mail = Email.text.toString().trim()
            var Pass = Password.text.toString().trim()
            if (mail.isEmpty()||Pass.isEmpty()){
                Toast.makeText(this, "Cannot submit an empty field", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(mail,Pass).addOnCompleteListener(this){
                    if (it.isSuccessful){
                        Toast.makeText(this, "Login successfull", Toast.LENGTH_SHORT).show()
                        val proceed = Intent(this,First::class.java)
                        startActivity(proceed)
                        finish()
                    }else{
                        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        Btn_reg.setOnClickListener() {
            var goToReg = Intent(this, Register_activity::class.java)
            startActivity(goToReg)
        }
    }
}
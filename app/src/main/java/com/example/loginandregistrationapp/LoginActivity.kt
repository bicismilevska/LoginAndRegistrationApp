package com.example.loginandregistrationapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
       // this.supportActionBar!!.hide()
        buttonLogin.setOnClickListener{
            var email=login_email.text.toString()
            var password=login_password.text.toString()
            Log.d("MainActivity","email is "+ email+ "Password is "+ password)
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{
                    if (it.isSuccessful) {
                        Log.d("LoginActivity", "signInWithEmail:success")
                        var intent= Intent(this,HomePage::class.java)
                        //clear all of your previous activity of your stack
                        intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)

                        startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("LoginActivity", "signInWithEmail:failure", it.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }
        }
        }




    }



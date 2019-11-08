package com.example.loginandregistrationapp
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar!!.hide()
        setContentView(R.layout.activity_main)
        textView.setOnClickListener{
            var intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        registerButton.setOnClickListener {
            performRegister()

        }
    }
    private fun performRegister(){
        var username = username_field.text.toString()
        var email = email_field.text.toString()
        var password = password_field.text.toString()
        if(email.isEmpty() || password.isEmpty() || username.isEmpty()){
            Toast.makeText(this,"All fields should be completed", Toast.LENGTH_LONG).show()
            return
        }
        Log.i("MainActivity", "Register button is clicked and  $username and $password")
        //here comes the authentication
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if(!it.isSuccessful) return@addOnCompleteListener
                Log.i("main","The creation of user is successful with uid:  ${it.result!!.user!!.uid}")
                var intent=Intent(this,HomePage::class.java)
                //clear all of your previous activity of your stack
                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra("Username", username)
                startActivity(intent)
            }
            .addOnFailureListener{
                Log.i("Main","User registration failed "+ it.message)
                Toast.makeText(this,"Failed to create user ${it.message}", Toast.LENGTH_LONG).show()
            }
    }
}

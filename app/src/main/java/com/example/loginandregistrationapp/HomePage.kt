package com.example.loginandregistrationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        supportActionBar?.title="Home"
        verifyIfUserIsLogged()
        var username: String = intent.getStringExtra("Username")
        welcometext.text="Welcome $username"
    }
    private fun verifyIfUserIsLogged(){
        var uid= FirebaseAuth.getInstance().uid
        if(uid==null){
            var intent= Intent(this,MainActivity::class.java)
            //clear all of your previous activity of your stack
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){

            R.id.log_out ->{
                FirebaseAuth.getInstance().signOut()
                var intent= Intent(this,MainActivity::class.java)
                //clear all of your previous activity of your stack
                intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
}

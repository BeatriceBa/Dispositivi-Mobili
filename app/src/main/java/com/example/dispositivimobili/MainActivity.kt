package com.example.dispositivimobili

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

import kotlinx.android.synthetic.main.activity_main.*
import android.os.StrictMode
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.TextView
import android.widget.Toast
import org.json.JSONObject
import java.net.URL

var sessionID = ""
class MainActivity : AppCompatActivity() {

    private lateinit var loginBtn : Button
    private lateinit var signInBtn : Button
    private lateinit var email : TextView
    private lateinit var password : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginBtn = findViewById(R.id.login)
        signInBtn = findViewById(R.id.signIn)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        setButtons()
    }

    private fun setButtons() {
        loginBtn.setOnClickListener{
            println("login")
            var emailstring = email.text.toString()
            var passwordstring = password.text.toString()

            if ( (emailstring == "") or (passwordstring == "") ) {
                Toast.makeText(this, "Inserisci email e password", Toast.LENGTH_LONG).show()
            }
            else {
                var jsonobject = JSONObject(URL
                    ("https://movieapi.magikarp.fun/login?email="+emailstring+"&password="+passwordstring).readText())
                if (jsonobject.getString("status") == "200") {
                    sessionID = jsonobject.getString("session")
                    startActivity(Intent(this, AppActivity::class.java))
                } else {
                    Toast.makeText(this, "Errore! email o password non corretti", Toast.LENGTH_LONG).show()
                }
            }
        }
        signInBtn.setOnClickListener{
            var emailstr = email.text.toString()
            var passwordstr = password.text.toString()
            if((emailstr == "") or (passwordstr == "")) {
                Toast.makeText(this, "Inserisci email e password", Toast.LENGTH_LONG).show()
            } else {
                var jsonobject = JSONObject(URL
                    ("https://movieapi.magikarp.fun/register?email="+emailstr+"&password="+passwordstr+"&username="+emailstr).readText())
                if (jsonobject.getString("status") == "200") {
                    Toast.makeText(this, "Registrato! Puoi usare la tua email e password per entrare", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Errore server :'(", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

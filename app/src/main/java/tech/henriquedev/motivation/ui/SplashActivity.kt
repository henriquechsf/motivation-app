package tech.henriquedev.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_splash.*
import tech.henriquedev.motivation.R
import tech.henriquedev.motivation.infra.SecurityPreferences

class SplashActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurityPreferences = SecurityPreferences(this)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        buttonSave.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonSave) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = editName.text.toString()

        if (name != "") {
            mSecurityPreferences.storeString("name", name)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Informe seu nome", Toast.LENGTH_SHORT).show()
        }
    }
}
package tech.henriquedev.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import tech.henriquedev.motivation.R
import tech.henriquedev.motivation.infra.MotivationConstants
import tech.henriquedev.motivation.infra.SecurityPreferences

class MainActivity : AppCompatActivity() {
    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSecurityPreferences = SecurityPreferences(this)
        textName.text = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
    }
}
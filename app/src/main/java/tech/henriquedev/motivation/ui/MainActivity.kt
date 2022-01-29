package tech.henriquedev.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import tech.henriquedev.motivation.R
import tech.henriquedev.motivation.infra.MotivationConstants
import tech.henriquedev.motivation.infra.SecurityPreferences
import tech.henriquedev.motivation.repository.Mock

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mSecurityPreferences: SecurityPreferences
    private var mPhraseFilter: Int = MotivationConstants.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        mSecurityPreferences = SecurityPreferences(this)
        textName.text ="Ola " + mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)

        // Logica inicial de selecao
        imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
        handleNewFrase()

        buttonNewFrase.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageMorning.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id

        val listFilters = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageMorning)

        if (id == R.id.buttonNewFrase) {
            handleNewFrase()
        } else if (id in listFilters) {
            handleFilter(id)
        }
    }

    private fun handleFilter(id: Int) {
        imageAll.setColorFilter(resources.getColor(R.color.white))
        imageHappy.setColorFilter(resources.getColor(R.color.white))
        imageMorning.setColorFilter(resources.getColor(R.color.white))

        when (id) {
            R.id.imageAll -> {
                imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.imageHappy -> {
                imageHappy.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.imageMorning -> {
                imageMorning.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.MORNING
            }
        }
    }

    private fun handleNewFrase() {
        textFrase.text = Mock().getPhrase(mPhraseFilter)
    }
}
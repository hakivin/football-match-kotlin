package com.hakivin.footballleague.ui.team

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hakivin.footballleague.R
import kotlinx.android.synthetic.main.activity_team.*

class TeamActivity : AppCompatActivity() {

    private var id : String? = null
    companion object {
        const val EXTRA_TEAM = "extra team"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)
        setSupportActionBar(toolbar)
        id = intent.getStringExtra(EXTRA_TEAM)
    }
}

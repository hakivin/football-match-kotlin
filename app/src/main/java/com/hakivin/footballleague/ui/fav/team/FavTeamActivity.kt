package com.hakivin.footballleague.ui.fav.team

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hakivin.footballleague.R
import com.hakivin.footballleague.db.database
import com.hakivin.footballleague.model.TeamItem
import kotlinx.android.synthetic.main.activity_fav_team.*

class FavTeamActivity : AppCompatActivity(), FavTeamView {

    private lateinit var presenter : FavTeamPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav_team)
        presenter = FavTeamPresenter(this)
        presenter.getFavTeams(database)
    }

    override fun onResume() {
        presenter.getFavTeams(database)
        super.onResume()
    }

    override fun showFavTeams(list: List<TeamItem>?) {
        rv_team_fav.layoutManager = LinearLayoutManager(applicationContext)
        rv_team_fav.adapter = list?.let { FavTeamAdapter(it) }
    }
}

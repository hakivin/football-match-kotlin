package com.hakivin.footballleague.ui.team

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.hakivin.footballleague.R
import com.hakivin.footballleague.db.database
import com.hakivin.footballleague.model.TeamItem
import com.hakivin.footballleague.remote.Api
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team.*
import kotlinx.android.synthetic.main.content_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamActivity : AppCompatActivity(), TeamDetailView {

    private var id : String? = null
    private lateinit var menuItem: Menu
    private var isFavourite = false
    private lateinit var team : TeamItem

    companion object {
        const val EXTRA_TEAM = "extra team"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)
        toolbar.title = " "
        setSupportActionBar(toolbar)
        id = intent.getStringExtra(EXTRA_TEAM)
        favouriteState()
        val presenter = TeamDetailPresenter(Api(), Gson(), this)
        presenter.getTeam(id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fav_menu, menu)
        if (menu != null) {
            menuItem = menu
        }
        setFavourite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.fav_match){
            if (isFavourite) removeFromFavourite() else addToFavourite()
            isFavourite = !isFavourite
            setFavourite()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addToFavourite() {
        try {
            database.use {
                insert(TeamItem.TABLE_TEAM,
                    TeamItem.ID_TEAM to team.id,
                    TeamItem.TEAM_NAME to team.name,
                    TeamItem.TEAM_ALTERNATE to team.alternate,
                    TeamItem.TEAM_BANNER to team.banner,
                    TeamItem.TEAM_DESC to team.desc,
                    TeamItem.TEAM_FORMEDYEAR to team.formedYear,
                    TeamItem.TEAM_LOGO to team.logo,
                    TeamItem.TEAM_LOCATION to team.location,
                    TeamItem.TEAM_STADIUM to team.stadium,
                    TeamItem.TEAM_MANAGER to team.manager,
                    TeamItem.TEAM_TYPE to team.type)
            }
            Snackbar.make(coordinator_lay, "${team.name} was added to Favourite", Snackbar.LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException){
            Snackbar.make(coordinator_lay, "Something went wrong", Snackbar.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    private fun removeFromFavourite(){
        try {
            database.use {
                delete(TeamItem.TABLE_TEAM, "(ID_TEAM = {id})",
                    "id" to id!!)
            }
            Snackbar.make(coordinator_lay, "${team.name} was removed from Favourite", Snackbar.LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException){
            Snackbar.make(coordinator_lay, "Something went wrong", Snackbar.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    private fun favouriteState(){
        database.use {
            val result = select(TeamItem.TABLE_TEAM)
                .whereArgs("(ID_TEAM = {id})",
                    "id" to id!!)
            val favorite = result.parseList(classParser<TeamItem>())
            if (favorite.isNotEmpty()) isFavourite = true
        }
    }

    private fun setFavourite() {
        if (isFavourite)
            menuItem.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_not_star_black_24dp)
        else
            menuItem.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_black_24dp)
    }

    @SuppressLint("SetTextI18n")
    override fun showTeam(team: List<TeamItem>?) {
        runOnUiThread {
            val currentTeam = team?.get(0)
            if (currentTeam != null) {
                this.team = currentTeam
            }
            Picasso.get().load(currentTeam?.banner).into(team_banner)
            Picasso.get().load(currentTeam?.logo).into(team_logo_detail)
            team_name_detail.text = "${currentTeam?.name} (${currentTeam?.alternate})"
            team_manager_detail.text = "Manager : ${currentTeam?.manager}"
            team_stadium_detail.text = "Stadium : ${currentTeam?.stadium}, ${currentTeam?.location}"
            team_formed_year_detail.text = "Formed Year : ${currentTeam?.formedYear}"
            team_desc_detail.text = "Overview : \n${currentTeam?.desc}"

            menuItem.getItem(0).isVisible = true
        }
    }
}

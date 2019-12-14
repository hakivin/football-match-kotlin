package com.hakivin.footballleague.ui.event

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
import com.hakivin.footballleague.model.EventItem
import com.hakivin.footballleague.model.TeamItem
import com.hakivin.footballleague.remote.Api
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class EventActivity : AppCompatActivity(), EventView {
    private lateinit var event : EventItem
    private lateinit var menuItem: Menu
    private lateinit var presenter: EventPresenter
    private var isFavourite = false
    private lateinit var eventId : String
    companion object {
        const val EXTRA_EVENT = "extra event"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        eventId = intent.getIntExtra(EXTRA_EVENT,0).toString()
        favouriteState()
        val request = Api()
        val gson = Gson()
        presenter = EventPresenter(this, request, gson)
        presenter.getEvent(eventId.toInt())
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
                insert(EventItem.TABLE_EVENT,
                    EventItem.ID_EVENT to event.id,
                    EventItem.HOME_TEAM to event.home,
                    EventItem.AWAY_TEAM to event.away,
                    EventItem.HOME_SCORE to event.homeScore.toString(),
                    EventItem.AWAY_SCORE to event.awayScore.toString(),
                    EventItem.DATE_EVENT to event.date,
                    EventItem.GOAL_HOME to event.goalHome,
                    EventItem.GOAL_AWAY to event.goalAway,
                    EventItem.GK_HOME to event.gkHome,
                    EventItem.GK_AWAY to event.gkAway,
                    EventItem.DEF_HOME to event.defHome,
                    EventItem.DEF_AWAY to event.defAway,
                    EventItem.MID_HOME to event.midHome,
                    EventItem.MID_AWAY to event.midAway,
                    EventItem.FWD_HOME to event.fwdHome,
                    EventItem.FWD_AWAY to event.fwdAway,
                    EventItem.SUB_HOME to event.subHome,
                    EventItem.SUB_AWAY to event.subAway,
                    EventItem.TYPE_EVENT to event.type)
            }
            Snackbar.make(coordinator_lays, "This event was added to Favourite", Snackbar.LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException){
            Snackbar.make(coordinator_lays, "Something went wrong", Snackbar.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    private fun removeFromFavourite(){
        try {
            database.use {
                delete(EventItem.TABLE_EVENT, "(ID_EVENT = {id})",
                    "id" to eventId)
            }
            Snackbar.make(coordinator_lays, "This event was removed from Favourite", Snackbar.LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException){
            Snackbar.make(coordinator_lays, "Something went wrong", Snackbar.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    private fun favouriteState(){
        database.use {
            val result = select(EventItem.TABLE_EVENT)
                .whereArgs("(ID_EVENT = {id})",
                    "id" to eventId)
            val favorite = result.parseList(classParser<EventItem>())
            if (favorite.isNotEmpty()) isFavourite = true
        }
    }

    private fun setFavourite() {
        if (isFavourite)
            menuItem.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_not_star_black_24dp)
        else
            menuItem.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_black_24dp)
    }

    override fun showEvent(events: List<EventItem>?) {
        runOnUiThread {
            val event = events?.get(0)
            if (event != null) {
                this.event = event
            }
            presenter.getHomeTeam(event?.idHome?.toInt())
            presenter.getAwayTeam(event?.idAway?.toInt())
            home_name.text = event?.home
            away_name.text = event?.away
            if (event?.homeScore != null)
                home_score.text = event.homeScore.toString()
            if (event?.awayScore != null)
                away_score.text = event.awayScore.toString()
            home_goal.text = event?.goalHome
            away_goal.text = event?.goalAway
            home_gk.text = event?.gkHome
            away_gk.text = event?.gkAway
            home_def.text = event?.defHome
            away_def.text = event?.defAway
            home_mid.text = event?.midHome
            away_mid.text = event?.midAway
            home_fwd.text = event?.fwdHome
            away_fwd.text = event?.fwdAway
            home_sub.text = event?.subHome
            away_sub.text = event?.subAway

            menuItem.getItem(0).isVisible = true
        }
    }

    override fun showHomeTeam(team: List<TeamItem>?) {
        runOnUiThread {
            Picasso.get().load(team?.get(0)?.logo).into(home_logo)
        }
    }

    override fun showAwayTeam(team: List<TeamItem>?) {
        runOnUiThread {
            Picasso.get().load(team?.get(0)?.logo).into(away_logo)
        }
    }
}

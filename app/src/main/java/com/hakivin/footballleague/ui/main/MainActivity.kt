package com.hakivin.footballleague.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hakivin.footballleague.R
import com.hakivin.footballleague.model.League
import com.hakivin.footballleague.ui.fav.event.FavouriteActivity
import com.hakivin.footballleague.ui.fav.team.FavTeamActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private var items: MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val badges = resources.obtainTypedArray(R.array.league_badge)
        val names = resources.getStringArray(R.array.league_name)
        val ids = resources.getIntArray(R.array.league_id)
        val descs = resources.getStringArray(R.array.league_desc)
        items.clear()
        for(i in names.indices){
            items.add(
                League(
                    ids[i],
                    names[i],
                    badges.getResourceId(i, 0),
                    descs[i]
                )
            )
        }
        badges.recycle()
        MainActivityUI(items).setContentView(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favourite -> startActivity<FavouriteActivity>()
            R.id.favourite2 -> startActivity<FavTeamActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

    class MainActivityUI(private val items : MutableList<League>) : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout {
                recyclerView {
                    lparams(width = matchParent, height = matchParent)
                    layoutManager = LinearLayoutManager(ctx)
                    adapter =
                        LeagueAdapter(items)
                }
            }
        }
    }
}

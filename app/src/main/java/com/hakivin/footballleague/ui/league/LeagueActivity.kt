package com.hakivin.footballleague.ui.league

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.hakivin.footballleague.R
import com.hakivin.footballleague.model.LeagueItem
import com.hakivin.footballleague.remote.Api
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_league.*
import org.jetbrains.anko.find

class LeagueActivity : AppCompatActivity(), LeagueView {
    companion object {
        const val EXTRA_LEAGUE = "extra league"
    }
    private lateinit var toolbar : Toolbar
    private lateinit var imgLogo : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_league)
        toolbar = find(R.id.toolbar)
        toolbar.title = " "
        setSupportActionBar(toolbar)
        val idLeague = intent.getIntExtra(EXTRA_LEAGUE, 0)
        val request = Api()
        val gson = Gson()
        val presenter = LeaguePresenter(this, request, gson)
        presenter.getLeague(idLeague)
        imgLogo = find(R.id.img_logo)

        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                this,
                idLeague,
                supportFragmentManager
            )
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun showLeague(datas: List<LeagueItem>?) {
        val data = datas?.get(0)
        val collapsingToolbarLayout =
            find(R.id.collapsingToolbarLayout) as CollapsingToolbarLayout
        val appBarLayout = find(R.id.appBarLayout) as AppBarLayout
        appBarLayout.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.title = data?.name
                    isShow = true
                } else if (isShow) {
                    collapsingToolbarLayout.title =
                        " " //careful there should a space between double quote otherwise it wont work
                    isShow = false
                }
            }
        })
        Picasso.get().load(data?.badge).into(imgLogo)
        league_desc.text = data?.description
    }
}
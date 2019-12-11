package com.hakivin.footballleague.ui.fav

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hakivin.footballleague.R
import com.hakivin.footballleague.db.database
import com.hakivin.footballleague.model.EventItem
import kotlinx.android.synthetic.main.activity_favourite.*

class FavouriteActivity : AppCompatActivity(), FavouriteView {
    private lateinit var presenter : FavouritePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)
        presenter = FavouritePresenter(this)
        presenter.getFavEvents(database)
    }

    override fun onResume() {
        presenter.getFavEvents(database)
        super.onResume()
    }

    override fun getFavEvents(events: List<EventItem>) {
        rv_fav.layoutManager = LinearLayoutManager(applicationContext)
        rv_fav.adapter = FavouriteAdapter(events)
    }
}

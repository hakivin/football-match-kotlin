package com.hakivin.footballleague.ui.league.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_overview.*
import com.hakivin.footballleague.R
import com.hakivin.footballleague.model.LeagueItem
import com.hakivin.footballleague.remote.Api
import com.hakivin.footballleague.ui.league.LeaguePresenter
import com.hakivin.footballleague.ui.league.LeagueView

/**
 * A simple [Fragment] subclass.
 */
class OverviewFragment : Fragment(), LeagueView {
    private var idLeague : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        idLeague = arguments!!.getInt("idLeague")
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val presenter = LeaguePresenter(this, Api(), Gson())
        presenter.getLeague(idLeague)
    }

    override fun showLeague(datas: List<LeagueItem>?) {
        league_desc.text = datas?.get(0)?.description
    }

}

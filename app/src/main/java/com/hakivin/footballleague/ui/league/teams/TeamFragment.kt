package com.hakivin.footballleague.ui.league.teams


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson

import com.hakivin.footballleague.R
import com.hakivin.footballleague.model.TeamItem
import com.hakivin.footballleague.remote.Api
import kotlinx.android.synthetic.main.fragment_team.*

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment(), TeamView {
    private var idLeague : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        idLeague = arguments!!.getInt("idLeague")
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val presenter = TeamPresenter(Api(), Gson(), this)
        presenter.getTeams(idLeague)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.action_menu, menu)
        val searchItem = menu.findItem(R.id.searchMenu)
        val searchView = searchItem.actionView as SearchView
    }
    override fun showTeams(list: List<TeamItem>?) {
        rv_team.layoutManager = LinearLayoutManager(context)
        rv_team.adapter = list?.let { TeamAdapter(it) }
    }
}

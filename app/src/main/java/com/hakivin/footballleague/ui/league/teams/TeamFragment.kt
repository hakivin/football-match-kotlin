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
import org.jetbrains.anko.support.v4.runOnUiThread

class TeamFragment : Fragment(), TeamView {
    private var idLeague : Int = 0
    private lateinit var presenter: TeamPresenter

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
        presenter = TeamPresenter(Api(), Gson(), this)
        presenter.getTeams(idLeague)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.action_menu, menu)
        val searchItem = menu.findItem(R.id.searchMenu)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query.isNullOrEmpty())
                    false
                else {
                    presenter.searchTeam(query)
                    true
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                presenter.getTeams(idLeague)
                return true
            }

        })
    }
    override fun showTeams(list: List<TeamItem>?) {
        runOnUiThread {
            rv_team.layoutManager = LinearLayoutManager(context)
            rv_team.adapter = list?.let { TeamAdapter(it) }
        }
    }

    override fun showSearchedTeams(list: List<TeamItem>?) {
        runOnUiThread {
            val filtered = arrayListOf<TeamItem>()
            if (list != null) {
                for (team in list){
                    if (team.type.equals("Soccer"))
                        filtered.add(team)
                }
            }
            rv_team.layoutManager = LinearLayoutManager(context)
            rv_team.adapter = TeamAdapter(filtered)
        }
    }
}

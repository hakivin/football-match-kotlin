package com.hakivin.footballleague.ui.league.match.next

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.hakivin.footballleague.R
import com.hakivin.footballleague.model.EventItem
import com.hakivin.footballleague.remote.Api
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.runOnUiThread

class NextMatchFragment : Fragment(), NextMatchView {

    private lateinit var rv : RecyclerView
    private lateinit var presenter : NextMatchPresenter
    private var idLeague : Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        idLeague = arguments!!.getInt("idLeague")
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv = find(R.id.rv_next)
        val request = Api()
        val gson = Gson()
        presenter = NextMatchPresenter(this, request, gson)
        presenter.getNextMatches(idLeague)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val searchItem = menu.findItem(R.id.searchMenu)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query.isNullOrEmpty())
                    false
                else {
                    presenter.searchMatches(query)
                    true
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                presenter.getNextMatches(idLeague)
                return true
            }

        })
    }

    override fun showEvents(list: List<EventItem>?) {
        runOnUiThread {
            rv.layoutManager = LinearLayoutManager(context)
            rv.adapter =
                list?.let { NextMatchAdapter(it) }
        }
    }
}

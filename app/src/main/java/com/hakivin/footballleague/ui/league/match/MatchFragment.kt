package com.hakivin.footballleague.ui.league.match

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
import kotlinx.android.synthetic.main.fragment_previous_match.*
import org.jetbrains.anko.support.v4.runOnUiThread

class MatchFragment : Fragment(),
    MatchView {
    private lateinit var rvPrev : RecyclerView
    private lateinit var rvNext : RecyclerView
    private lateinit var presenter : MatchPresenter
    private var idLeague : Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        idLeague = arguments!!.getInt("idLeague")
        return inflater.inflate(R.layout.fragment_previous_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvPrev = rv_prev
        rvNext = rv_next
        val request = Api()
        val gson = Gson()
        presenter =
            MatchPresenter(
                this,
                request,
                gson
            )
        presenter.getPreviousMatches(idLeague)
        presenter.getNextMatches(idLeague)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.action_menu, menu)
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
                presenter.getPreviousMatches(idLeague)
                presenter.getNextMatches(idLeague)
                return true
            }

        })
    }

    override fun showPastEvents(list: List<EventItem>?) {
        runOnUiThread {
            rvPrev.layoutManager = LinearLayoutManager(context)
            rvPrev.adapter = list?.let {
                MatchAdapter(
                    it
                )
            }
        }
    }

    override fun showNextEvents(list: List<EventItem>?) {
        runOnUiThread {
            rvNext.layoutManager = LinearLayoutManager(context)
            rvNext.adapter = list?.let {
                MatchAdapter(
                    it
                )
            }
        }
    }

    override fun showSearchedEvents(list: List<EventItem>?) {
        runOnUiThread {
            val prevList = arrayListOf<EventItem>()
            val nextList = arrayListOf<EventItem>()
            if (list != null) {
                for (event in list){
                    if (event.homeScore.toString() == "null")
                        nextList.add(event)
                    else
                        prevList.add(event)
                }
            }
            rvPrev.layoutManager = LinearLayoutManager(context)
            rvNext.layoutManager = LinearLayoutManager(context)
            rvPrev.adapter = list?.let {
                MatchAdapter(
                    prevList
                )
            }
            rvNext.adapter = list?.let {
                MatchAdapter(
                    nextList
                )
            }
        }
    }
}

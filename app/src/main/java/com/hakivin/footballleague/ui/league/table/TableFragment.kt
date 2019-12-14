package com.hakivin.footballleague.ui.league.table

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson

import com.hakivin.footballleague.R
import com.hakivin.footballleague.model.TableItem
import com.hakivin.footballleague.remote.Api
import kotlinx.android.synthetic.main.fragment_table.*
import org.jetbrains.anko.support.v4.ctx

/**
 * A simple [Fragment] subclass.
 */
class TableFragment : Fragment(), TableView {
    private var idLeague : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        idLeague = arguments!!.getInt("idLeague")
        return inflater.inflate(R.layout.fragment_table, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val presenter = TablePresenter(this, Api(), Gson())
        presenter.getStandings(idLeague)
    }

    override fun getStandings(standings: List<TableItem>?) {
        rv_table.layoutManager = LinearLayoutManager(ctx)
        rv_table.adapter = standings?.let { TableAdapter(it) }
    }
}

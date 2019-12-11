package com.hakivin.footballleague.ui.main

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hakivin.footballleague.R
import com.hakivin.footballleague.model.League
import com.hakivin.footballleague.ui.league.LeagueActivity
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class LeagueAdapter (private val items: MutableList<League>) : RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>(){

    class LeagueUI : AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                verticalLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.league_badge
                    }.lparams{
                        height = dip(90)
                        width = dip(90)
                    }
                    textView {
                        id = R.id.league_tv
                        textSize = 16f
                    }.lparams{margin = dip(16)}
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(
            LeagueUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class LeagueViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val leagueBadge : ImageView = view.find(R.id.league_badge)
        private val leagueName : TextView = view.find(R.id.league_tv)

        fun bind(league: League){
            leagueName.text = league.name
            league.badge.let {
                if (it != null) {
                    Picasso.get().load(it).fit().into(leagueBadge)
                }
            }
            itemView.setOnClickListener {
                itemView.context.startActivity<LeagueActivity>(
                    LeagueActivity.EXTRA_LEAGUE to league.id)
            }
        }
    }
}
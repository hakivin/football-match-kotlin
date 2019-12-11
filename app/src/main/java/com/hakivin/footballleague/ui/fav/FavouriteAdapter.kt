package com.hakivin.footballleague.ui.fav

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hakivin.footballleague.R
import com.hakivin.footballleague.model.EventItem
import com.hakivin.footballleague.ui.event.EventActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class FavouriteAdapter (private val items: List<EventItem>) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val home : TextView = view.find(R.id.tv_match_home)
        private val away : TextView = view.find(R.id.tv_match_away)
        private val score : TextView = view.find(R.id.tv_match_score)
        private val date : TextView = view.find(R.id.tv_match_date)

        @SuppressLint("SetTextI18n")
        fun bind(event: EventItem){
            home.text = event.home
            away.text = event.away
            println("score = ${event.homeScore}")
            if (event.homeScore.equals("null"))
                score.text = " vs "
            else
                score.text = "${event.homeScore} vs ${event.awayScore}"
            date.text = event.date

            itemView.setOnClickListener {
                itemView.context.startActivity<EventActivity>(EventActivity.EXTRA_EVENT to event.id?.toInt())
            }
        }
    }
}
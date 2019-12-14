package com.hakivin.footballleague.ui.league.teams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hakivin.footballleague.R
import com.hakivin.footballleague.model.TeamItem
import com.hakivin.footballleague.ui.team.TeamActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_list_item.view.*
import org.jetbrains.anko.startActivity

class TeamAdapter (val list: List<TeamItem>): RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.team_list_item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(team : TeamItem){
            Picasso.get().load(team.logo).into(itemView.team_list_image)
            itemView.team_list_name.text = team.name
            itemView.team_list_stadium.text = team.stadium

            itemView.setOnClickListener {
                itemView.context.startActivity<TeamActivity>(TeamActivity.EXTRA_TEAM to team.id)
            }
        }
    }
}
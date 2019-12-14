package com.hakivin.footballleague.ui.league.table

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hakivin.footballleague.R
import com.hakivin.footballleague.model.TableItem
import com.hakivin.footballleague.ui.team.TeamActivity
import kotlinx.android.synthetic.main.team_item.view.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.startActivity

class TableAdapter (private val list : List<TableItem>) :
    RecyclerView.Adapter<TableAdapter.TableViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.team_item, parent, false)
        return TableViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.bind(list[position], position+1)
    }

    class TableViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(table: TableItem, position: Int){
            itemView.team_rank.text = position.toString()
            itemView.team_name.text = table.teamName
            itemView.team_played.text = table.played.toString()
            itemView.team_win.text = table.win.toString()
            itemView.team_draw.text = table.draw.toString()
            itemView.team_lose.text = table.lose.toString()
            itemView.team_goal_for.text = table.goalsFor.toString()
            itemView.team_goal_against.text = table.goalsAgainst.toString()
            itemView.team_goal_diff.text = table.goalsDiff.toString()
            itemView.team_point.text = table.point.toString()
            if (position % 2 == 0)
                itemView.backgroundColor = ContextCompat.getColor(itemView.context, R.color.colorAccentLight)
            itemView.setOnClickListener {
                itemView.context.startActivity<TeamActivity>(TeamActivity.EXTRA_TEAM to table.teamId)
            }
        }
    }

}
package com.hakivin.footballleague.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.hakivin.footballleague.model.EventItem
import org.jetbrains.anko.db.*

class DatabaseHelper (ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavouriteMatch.db", null, 1){
    companion object {
        private var instance : DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            if (instance == null)
                instance = DatabaseHelper(ctx.applicationContext)
            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(EventItem.TABLE_EVENT, true,
            EventItem.ID_EVENT to TEXT + PRIMARY_KEY,
            EventItem.HOME_TEAM to TEXT,
            EventItem.AWAY_TEAM to TEXT,
            EventItem.HOME_SCORE to TEXT,
            EventItem.AWAY_SCORE to TEXT,
            EventItem.DATE_EVENT to TEXT,
            EventItem.ID_TEAM_HOME to TEXT,
            EventItem.ID_TEAM_AWAY to TEXT,
            EventItem.GOAL_HOME to TEXT,
            EventItem.GOAL_AWAY to TEXT,
            EventItem.GK_HOME to TEXT,
            EventItem.GK_AWAY to TEXT,
            EventItem.DEF_HOME to TEXT,
            EventItem.DEF_AWAY to TEXT,
            EventItem.MID_HOME to TEXT,
            EventItem.MID_AWAY to TEXT,
            EventItem.FWD_HOME to TEXT,
            EventItem.FWD_AWAY to TEXT,
            EventItem.SUB_HOME to TEXT,
            EventItem.SUB_AWAY to TEXT,
            EventItem.TYPE_EVENT to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(EventItem.TABLE_EVENT, true)
    }
}

val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)
package com.hakivin.footballleague.ui.league

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hakivin.footballleague.R
import com.hakivin.footballleague.ui.league.match.next.NextMatchFragment
import com.hakivin.footballleague.ui.league.match.past.PreviousMatchFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, private val id: Int?, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        val fragment : Fragment =
            if(position == 0)
                PreviousMatchFragment()
            else
                NextMatchFragment()
        val bundle = Bundle()
        if (id != null) {
            bundle.putInt("idLeague", id)
        }
        fragment.arguments = bundle
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}
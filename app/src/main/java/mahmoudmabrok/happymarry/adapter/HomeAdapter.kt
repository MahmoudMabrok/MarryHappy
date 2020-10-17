package mahmoudmabrok.happymarry.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import mahmoudmabrok.happymarry.R
import mahmoudmabrok.happymarry.views.articles.ArticlListFragment
import mahmoudmabrok.happymarry.views.videos.VideosListFragment


class HomeAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val TAB_TITLES = arrayOf(
        R.string.videos,
        R.string.articls
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> VideosListFragment()
            else -> ArticlListFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
}
package mahmoudmabrok.happymarry.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import mahmoudmabrok.happymarry.R
import mahmoudmabrok.happymarry.views.non_video.NonVideoListFragment
import mahmoudmabrok.happymarry.views.videos.VideosListFragment

class HomeAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val titles = arrayOf(
        R.string.videos,
        R.string.articles
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> VideosListFragment()
            else -> NonVideoListFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(titles[position])
    }

    override fun getCount(): Int {
        return 2
    }
}
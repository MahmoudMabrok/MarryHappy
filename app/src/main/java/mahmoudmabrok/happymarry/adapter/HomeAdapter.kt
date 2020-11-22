package mahmoudmabrok.happymarry.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import mahmoudmabrok.happymarry.views.non_video.NonVideoListFragment
import mahmoudmabrok.happymarry.views.videos.VideosListFragment

class HomeAdapter(fm: FragmentActivity) :
    FragmentStateAdapter(fm) {


    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> VideosListFragment()
            else -> NonVideoListFragment()
        }
    }

}
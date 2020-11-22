package mahmoudmabrok.happymarry.views.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import mahmoudmabrok.happymarry.R
import mahmoudmabrok.happymarry.adapter.HomeAdapter
import mahmoudmabrok.happymarry.util.UpdateHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBody()
    }

    private fun setupBody() {
        val adapter = HomeAdapter(this)
        vpHome?.adapter = adapter

        val titles = arrayOf(
            R.string.videos,
            R.string.articles
        )


        TabLayoutMediator(tabs, vpHome) { tab, position ->
            tab.text = getString(titles[position])
        }.attach()


        UpdateHelper.checkUpdates(this)

    }

}
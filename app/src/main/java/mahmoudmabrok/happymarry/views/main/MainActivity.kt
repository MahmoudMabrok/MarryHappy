package mahmoudmabrok.happymarry.views.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        val adapter = HomeAdapter(this, supportFragmentManager)
        vpHome?.adapter = adapter
        tabs.setupWithViewPager(vpHome)
        UpdateHelper.checkUpdates(this)
        vpHome.offscreenPageLimit = 0

    }

}
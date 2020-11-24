package mahmoudmabrok.happymarry.views.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.vorlonsoft.android.rate.AppRate
import kotlinx.android.synthetic.main.activity_main.*
import mahmoudmabrok.happymarry.R
import mahmoudmabrok.happymarry.adapter.HomeAdapter
import mahmoudmabrok.happymarry.util.UpdateHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBody()

        initRate()
    }

    private fun initRate() {

        AppRate.with(this)
            .setInstallDays(2.toByte())
            .setLaunchTimes(4.toByte())
            .setRemindInterval(2.toByte())
            .setRemindLaunchesNumber(3.toByte())
            .setShowLaterButton(true)
            .monitor()

        AppRate.showRateDialogIfMeetsConditions(this)
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
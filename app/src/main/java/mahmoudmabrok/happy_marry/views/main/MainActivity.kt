package mahmoudmabrok.happy_marry.views.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.mahmoud_mabrouk.happy_marry.R
import edu.mahmoud_mabrouk.happy_marry.adapter.HomeAdapter
import edu.mahmoud_mabrouk.happy_marry.util.UpdateHelper
import kotlinx.android.synthetic.main.activity_main.*

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
package mahmoudmabrok.happymarry.views.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import mahmoudmabrok.happymarry.R
import mahmoudmabrok.happymarry.views.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        imageView?.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }, 1800)


    }
}
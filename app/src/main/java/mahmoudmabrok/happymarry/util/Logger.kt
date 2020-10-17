package mahmoudmabrok.happymarry.util

import android.util.Log
import mahmoudmabrok.happymarry.BuildConfig

object Logger {

    fun log(msg: String? = null, TAG: String? = "TestApp") {
        if (BuildConfig.DEBUG)
            Log.d(TAG, msg ?: "")
    }
}
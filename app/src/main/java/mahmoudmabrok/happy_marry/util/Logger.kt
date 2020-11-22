package edu.mahmoud_mabrouk.happy_marry.util

import android.util.Log
import edu.mahmoud_mabrouk.happy_marry.BuildConfig

object Logger {

    fun log(msg: String? = null, TAG: String? = "TestApp") {
        if (BuildConfig.DEBUG)
            Log.d(TAG, msg ?: "")
    }
}
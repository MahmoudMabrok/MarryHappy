package edu.mahmoud_mabrouk.happy_marry.util

import android.content.Context
import android.content.Intent
import android.net.Uri

object IntentHelper {

    fun openUrl(ctx: Context, url: String? = null) {
        ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url ?: "")))
    }
}
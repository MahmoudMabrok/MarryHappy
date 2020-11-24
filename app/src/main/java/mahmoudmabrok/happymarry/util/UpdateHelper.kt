package mahmoudmabrok.happymarry.util

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.tasks.Task

object UpdateHelper {

    private const val UPDATE_CODE = 10001

    fun checkUpdates(ctx: Context) {
        val updateManager = AppUpdateManagerFactory.create(ctx)
        val updateInfo = updateManager.appUpdateInfo
        updateInfo.addOnSuccessListener {
            handleImmediateUpdate(ctx, updateManager, updateInfo)
        }.addOnFailureListener {
            Logger.log("UpdateHelper checkUpdates: failed ${it.message}")
        }
    }

    private fun handleImmediateUpdate(
        ctx: Context,
        updateManager: AppUpdateManager?,
        updateInfo: Task<AppUpdateInfo>?
    ) {
        Logger.log("UpdateHelper handleImmediateUpdate: ")
        when (updateInfo?.result?.updateAvailability()) {
            UpdateAvailability.UPDATE_AVAILABLE -> {
                if (updateInfo.result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE))
                    updateManager?.startUpdateFlowForResult(
                        updateInfo.result,
                        AppUpdateType.IMMEDIATE,
                        ctx as Activity,
                        UPDATE_CODE
                    )
            }
            else -> {
                Toast.makeText(ctx, "You Are Up To Date", Toast.LENGTH_LONG).show()
            }
        }
    }
}
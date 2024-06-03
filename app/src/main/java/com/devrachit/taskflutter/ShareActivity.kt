package com.devrachit.taskflutter

import android.app.AlertDialog
import android.app.PictureInPictureParams
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Rational
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class ShareActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        // Set an empty view or use a simple layout with a TextView
//        setContentView(R.layout.activity_share)
//
//        // Handle the incoming intent
//        val intent = intent
//        if (Intent.ACTION_SEND == intent.action && intent.type != null) {
//            if ("text/plain" == intent.type) {
//                handleSendText(intent) // Handle text being sent
//            }
//        }
//    }

    fun handleSendText(intent: Intent) {
        val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
        if (sharedText != null) {
            // Show a dialog box
            AlertDialog.Builder(this)
                .setTitle("Shared Link")
                .setMessage(sharedText)
                .setPositiveButton(
                    "OK",
                    DialogInterface.OnClickListener { dialog, which -> // Close the dialog and activity
                        dialog.dismiss()
                        finish()
                    })
                .setIcon(R.drawable.ic_launcher_foreground)
                .show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            enterPipMode()
        }
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            enterPipMode()
        }
    }

    private fun enterPipMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val aspectRatio = Rational(16, 9)
            val pipBuilder = PictureInPictureParams.Builder()
            pipBuilder.setAspectRatio(aspectRatio)
            enterPictureInPictureMode(pipBuilder.build())
        }
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            enterPipMode()
        }
    }
}

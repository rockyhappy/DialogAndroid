package com.devrachit.taskflutter

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class ShareActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Set an empty view or use a simple layout with a TextView
        setContentView(R.layout.activity_share)

        // Handle the incoming intent
        val intent = intent
        if (Intent.ACTION_SEND == intent.action && intent.type != null) {
            if ("text/plain" == intent.type) {
                handleSendText(intent) // Handle text being sent
            }
        }
    }

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
}

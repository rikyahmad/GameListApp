package com.staygrateful.core.extension

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
 * Extension function to display a toast message.
 *
 * This extension function displays a toast message with the provided title for a short duration.
 *
 * @param title The title to be displayed in the toast message.
 */
fun Context.showToast(title: String) {
    // Display a toast message with the provided title
    Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
}

/**
 * Extension function to show a Snackbar message.
 *
 * This extension function displays a Snackbar message with the provided message text and optional action text.
 * It also provides an optional callback function to be executed when the action is clicked.
 *
 * @param message The message text to be displayed in the Snackbar.
 * @param actionText Optional text for the action button. If null, no action button will be shown.
 * @param onActionClick Optional callback function to be executed when the action button is clicked.
 */
fun View.showSnackbar(message: String, actionText: String? = null, onActionClick: () -> Unit = {}) {
    // Create a Snackbar with the provided message text and length
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)

    // Set action button if actionText is provided
    actionText?.let {
        snackbar.setAction(actionText) {
            // Execute the callback function when the action button is clicked
            onActionClick.invoke()
        }
    }

    // Show the Snackbar
    snackbar.show()
}

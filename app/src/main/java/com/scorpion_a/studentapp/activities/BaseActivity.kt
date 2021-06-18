package com.scorpion_a.studentapp.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.utils.Connection
import org.imaginativeworld.oopsnointernet.ConnectionCallback
import org.imaginativeworld.oopsnointernet.NoInternetDialog
import org.imaginativeworld.oopsnointernet.NoInternetSnackbar

open class BaseActivity : AppCompatActivity() {

    // No Internet Dialog
    private var noInternetDialog: NoInternetDialog? = null

    // No Internet Snackbar
    private var noInternetSnackbar: NoInternetSnackbar? = null

    // ...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        if (!Connection.isNetworkAvailable(this)){
          var snack=  Snackbar.make(getWindow().getDecorView().getRootView(), getString(R.string.pcyic), Snackbar.LENGTH_LONG)
            snack .setAction(
                getString(R.string.ok), View.OnClickListener {
                    snack.dismiss()
                }
            )
            snack.show()
        }


    }
    //    override fun onResume() {
//        super.onResume()
//
//        // No Internet Dialog
//        noInternetDialog = NoInternetDialog.Builder(this)
//            .apply {
//                connectionCallback = object : ConnectionCallback { // Optional
//                    override fun hasActiveConnection(hasActiveConnection: Boolean) {
//                        // ...
//                    }
//                }
//                cancelable = false // Optional
//                noInternetConnectionTitle = "No Internet" // Optional
//                noInternetConnectionMessage =
//                    "Check your Internet connection and try again." // Optional
//                showInternetOnButtons = true // Optional
//                pleaseTurnOnText = "Please turn on" // Optional
//                wifiOnButtonText = "Wifi" // Optional
//                mobileDataOnButtonText = "Mobile data" // Optional
//
//                onAirplaneModeTitle = "No Internet" // Optional
//                onAirplaneModeMessage = "You have turned on the airplane mode." // Optional
//                pleaseTurnOffText = "Please turn off" // Optional
//                airplaneModeOffButtonText = "Airplane mode" // Optional
//                showAirplaneModeOffButtons = true // Optional
//            }
//            .build()
//
//        // No Internet Snackbar
//        noInternetSnackbar =
//            NoInternetSnackbar.Builder(this, findViewById(android.R.id.content))
//                .apply {
//                    connectionCallback = object : ConnectionCallback { // Optional
//                        override fun hasActiveConnection(hasActiveConnection: Boolean) {
//                            // ...
//                        }
//                    }
//                    indefinite = true // Optional
//                    noInternetConnectionMessage = "No active Internet connection!" // Optional
//                    onAirplaneModeMessage = "You have turned on the airplane mode!" // Optional
//                    snackbarActionText = "Settings" // Optional
//                    showActionToDismiss = false // Optional
//                    snackbarDismissActionText = "OK" // Optional
//                }
//                .build()
//    }
//
//    override fun onPause() {
//        super.onPause()
//
//        // No Internet Dialog
//        noInternetDialog?.destroy()
//
//        // No Internet Snackbar
//        noInternetSnackbar?.destroy()
//    }
}
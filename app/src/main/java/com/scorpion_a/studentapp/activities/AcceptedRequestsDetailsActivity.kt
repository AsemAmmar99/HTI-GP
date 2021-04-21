package com.scorpion_a.studentapp.activities

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.Theme
import kotlinx.android.synthetic.main.activity_profile_page.*

class AcceptedRequestsDetailsActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var receiptImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accepted_requests_details)
        toolbar = header.findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.ard)

        receiptImage = findViewById(R.id.ivReceipt)
        val image = ImageView(this)
        image.setImageResource(R.drawable.receipt)

        receiptImage.setOnClickListener {
            if (image.getParent() != null) (image.getParent() as ViewGroup).removeView(
                image
            )
            var dialog = AlertDialog.Builder(this).setPositiveButton(getString(R.string.ok),
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {

                        dialog?.dismiss()
                    }

                })

            dialog.setView(image).create().show()
        }
    }
}
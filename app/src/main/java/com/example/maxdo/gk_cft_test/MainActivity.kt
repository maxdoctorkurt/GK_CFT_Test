package com.example.maxdo.gk_cft_test

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.maxdo.gk_cft_test.editing.ImageProcessingService
import com.example.maxdo.gk_cft_test.editing.history.EditingHistoryFragment
import com.example.maxdo.gk_cft_test.editing.modifiersPanel.EditingImageFragment
import pl.aprilapps.easyphotopicker.EasyImage
import pl.aprilapps.easyphotopicker.DefaultCallback
import android.widget.Toast
import java.io.File


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editingPanelFragmentNull =
            supportFragmentManager.findFragmentByTag(EditingImageFragment::class.java.simpleName) == null
        val editingHistoryFragmentNull =
            supportFragmentManager.findFragmentByTag(EditingHistoryFragment::class.java.simpleName) == null

        if (editingPanelFragmentNull || editingHistoryFragmentNull) {

            val ft = supportFragmentManager.beginTransaction()

            ft.replace(
                R.id.editing,
                EditingImageFragment(), EditingImageFragment::class.java.simpleName
            )
            ft.replace(R.id.list, EditingHistoryFragment(), EditingHistoryFragment::class.java.simpleName)

            ft.commit()


        }

        startService(Intent(this, ImageProcessingService::class.java))
    }


}

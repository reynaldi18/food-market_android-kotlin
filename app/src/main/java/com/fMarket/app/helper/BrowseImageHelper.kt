package com.fMarket.app.helper

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.fMarket.app.R
import java.io.File

object BrowseImageHelper {

    fun pickImage(activity: FragmentActivity?, fragment: Fragment? = null) {
        Dexter.withContext(fragment?.requireContext())
            .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?, token: PermissionToken?,
                ) {
                    Common.showMessageDialog(
                        activity,
                        getString(R.string.message_rationale_permission_image)
                    )
                }

                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report!!.areAllPermissionsGranted())
                        if (fragment != null) ImagePicker.with(fragment).start()
                        else activity?.let { ImagePicker.with(it).start() }
                    else Common.showToast(R.string.message_permission_image)
                }
            }).check()
    }

    fun handlePickedImage(
        activity: FragmentActivity?,
        resultCode: Int,
        data: Intent?,
        onPhotosReturned: (File) -> Unit,
    ) {
        if (activity != null) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val file: File = ImagePicker.getFile(data)!!
                    onPhotosReturned(file)
                }
                ImagePicker.RESULT_ERROR -> Toast.makeText(
                    getAppContext(),
                    ImagePicker.getError(data),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

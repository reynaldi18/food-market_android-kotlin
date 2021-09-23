package com.fMarket.app.helper

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.fMarket.app.BuildConfig
import com.fMarket.app.constant.Config
import com.fMarket.app.model.DownloadResult
import com.fMarket.app.model.downloadFile
import io.ktor.client.*
import io.ktor.client.engine.android.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

object UploadDownloadHelper {

    fun browseSaveFile(fragment: Fragment, fileName: String) {
        val permissionListener = object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                if (report.areAllPermissionsGranted()) {
                    proceedBrowseSaveFile(fragment, fileName)
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                p1: PermissionToken?,
            ) {
                // Empty body
            }
        }
        Dexter.withContext(fragment.requireContext())
            .withPermissions(
                listOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            )
            .withListener(permissionListener).check()
    }

    fun handleActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        onSaveFileDefined: (Uri) -> Unit
    ) {
        if (requestCode == Config.REQUEST_SAVE_FILE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri -> onSaveFileDefined(uri) }
        }
    }

    fun downloadFile(
        context: Context,
        url: String,
        file: Uri,
        onDownloading: () -> Unit,
        onDownloadError: () -> Unit,
        onDownloaded: (Uri) -> Unit,
        authorizationToken: String? = null,
    ) {
        val ktor = HttpClient(Android)
        onDownloading()
        context.contentResolver.openOutputStream(file)?.let { outputStream ->
            CoroutineScope(Dispatchers.IO).launch {
                ktor.downloadFile(outputStream, url, authorizationToken).collect {
                    withContext(Dispatchers.Main) {
                        when (it) {
                            is DownloadResult.Success -> onDownloaded(file)
                            is DownloadResult.Error -> onDownloadError()
                            is DownloadResult.Progress -> {
                            }
                        }
                    }
                }
            }
        }
    }

    fun viewFile(context: Context, uri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        val chooser = Intent.createChooser(intent, "Open with")

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(chooser)
        } else {
            Toast.makeText(context, "No suitable application to open file", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun proceedBrowseSaveFile(fragment: Fragment, fileName: String) {
        val context = fragment.requireContext()
        val folder = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
        val file = File(folder, fileName)
        val uri = context.let {
            FileProvider.getUriForFile(it, "${BuildConfig.APPLICATION_ID}.provider", file)
        }
        val extension = MimeTypeMap.getFileExtensionFromUrl(uri?.path)
        val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)

        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT)
        intent.setDataAndType(uri, mimeType)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.putExtra(Intent.EXTRA_TITLE, fileName)
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        fragment.startActivityForResult(intent, Config.REQUEST_SAVE_FILE)
    }
}

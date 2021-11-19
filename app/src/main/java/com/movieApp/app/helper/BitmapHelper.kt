package com.movieApp.app.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface
import android.os.Environment
import android.util.Base64
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImage
import com.movieApp.app.constant.Config
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.io.IOException


class BitmapHelper {
    companion object {
        fun calculateInSampleSize(
            options: BitmapFactory.Options,
            reqWidth: Int,
            reqHeight: Int
        ): Int {
            // Raw height and width of image
            val height = options.outHeight
            val width = options.outWidth
            var inSampleSize = 1

            if (height > reqHeight || width > reqWidth) {

                // Calculate ratios of height and width to requested height and width
                val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
                val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())

                // Choose the smallest ratio as inSampleSize value, this will guarantee
                // a final image with both dimensions larger than or equal to the
                // requested height and width.
                inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
            }

            return inSampleSize
        }

        fun decodeSampledBitmapFromFile(
            path: String?,
            reqWidth: Int = Config.DEFAULT_IMAGE_SIZE,
            reqHeight: Int = Config.DEFAULT_IMAGE_SIZE
        ): Bitmap {
            // First decode with inJustDecodeBounds=true to check dimensions
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(path, options)

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false

            //Unrotated bitmap
            val bitmap = BitmapFactory.decodeFile(path, options)

            //Rotation handler
            val ei = ExifInterface(path ?: "")
            val orientation: Int = ei.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED
            )

            return when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmap, 90)
                ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitmap, 180)
                ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitmap, 270)
                ExifInterface.ORIENTATION_NORMAL -> bitmap
                else -> bitmap
            }
        }


        fun resize(image: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
            var scaleImage = image
            if (maxHeight > 0 && maxWidth > 0) {
                val width = scaleImage.width
                val height = scaleImage.height
                val ratioBitmap = width.toFloat() / height.toFloat()
                val ratioMax = maxWidth.toFloat() / maxHeight.toFloat()

                var finalWidth = maxWidth
                var finalHeight = maxHeight
                if (ratioMax > ratioBitmap) {
                    finalWidth = (maxHeight.toFloat() * ratioBitmap).toInt()
                } else {
                    finalHeight = (maxWidth.toFloat() / ratioBitmap).toInt()
                }
                scaleImage = Bitmap.createScaledBitmap(scaleImage, finalWidth, finalHeight, true)
                return scaleImage
            } else
                return scaleImage
        }

        fun toBase64(bitmap: Bitmap?): String {
            val baos = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val imageBytes = baos.toByteArray()
            return "data:image/jpeg;base64," + Base64.encodeToString(imageBytes, Base64.NO_WRAP)
        }

        fun saveToFile(imagePath: String?, bitmap: Bitmap) {
            var out: FileOutputStream? = null
            try {
                out = FileOutputStream(imagePath)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out) // bmp is your Bitmap instance
                // PNG is a lossless format, the compression factor (100) is ignored
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    if (out != null) {
                        out.close()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }


        private fun dpToPx(context: Context, dp: Int): Int {
            val density = context.resources.displayMetrics.density
            return Math.round(dp.toFloat() * density)
        }

        fun getPictureDirectory(): String = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES
        ).absolutePath + "/BOS/"
    }
}


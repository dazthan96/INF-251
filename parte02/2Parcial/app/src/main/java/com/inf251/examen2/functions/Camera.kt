package com.inf251.examen2.functions

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import java.text.SimpleDateFormat
import java.util.*

fun CrearImageUri(context: Context):Uri?{
    val contentValues = ContentValues().apply {
        put(
            MediaStore.Images.Media.DISPLAY_NAME,
            "foto_${SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())}.jpg"
        )
        put(MediaStore.Images.Media.MIME_TYPE,"image/jpeg")
        put(MediaStore.Images.Media.RELATIVE_PATH,"Pictures/CameraCompose")
    }
    return context.contentResolver.insert(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        contentValues
    )
}
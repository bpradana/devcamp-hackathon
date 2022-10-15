package com.tkpd.hackathon17.utils

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.media.ExifInterface
import android.net.Uri
import android.provider.MediaStore

object Utils {
    @SuppressLint("Range")
    fun getPictureData(context: Context, uri: Uri): ExifInterface? {
        val uriParts = uri.toString().split(":").toTypedArray()
        var path: String? = null
        if (uriParts[0] == "content") {
            val col = MediaStore.Images.ImageColumns.DATA
            val c: Cursor? = context.getContentResolver().query(
                uri, arrayOf(col),
                null, null, null
            )
            if (c != null && c.moveToFirst()) {
                path = c.getString(c.getColumnIndex(col))
                c.close()
                return ExifInterface(path)
            }
        } else if (uriParts[0] == "file") {
            path = uri.encodedPath
            return ExifInterface(path!!)
        }
        return null
    }

}
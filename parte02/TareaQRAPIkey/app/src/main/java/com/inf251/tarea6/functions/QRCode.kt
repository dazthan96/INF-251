package com.inf251.tarea6.functions

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set

fun GenerarQr(text:String, size:Int=250): Bitmap?{
    if (text.isBlank()) return null
    return try {
        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, size,size)
        val bmp = createBitmap(size, size, Bitmap.Config.RGB_565)
        for(x in 0 until size){
            for (y in 0 until size){
                bmp[x, y] =
                    if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE
            }
        }
        bmp
    }catch (e: WriterException){
        null
    }
}
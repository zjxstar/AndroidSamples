package com.zjx.securitysample.md5

import java.io.IOException
import java.io.InputStream
import java.security.DigestInputStream
import java.security.MessageDigest

class MD5K {

    fun getMD5ForStr(str: String): String {
        val md5 = MessageDigest.getInstance(ALGORITHM_MD5)
        md5.update(str.toByteArray())
        return toHex(md5.digest())
    }

    fun getMD5ForFile(inputStream: InputStream): String {
        var dis: DigestInputStream? = null
        val buffer = ByteArray(1024)
        try {
            var md5 = MessageDigest.getInstance(ALGORITHM_MD5)
            dis = DigestInputStream(inputStream, md5)
            while (dis.read(buffer) > 0);
            md5 = dis.messageDigest
            return toHex(md5.digest())
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                dis?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return ""
    }

    private fun toHex(bytes: ByteArray): String {
        val hexDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
        val len = bytes.size
        val str = CharArray(len * 2) // 32
        var k = 0
        for (i in 0 until len) {
            val byteTemp = bytes[i]
            // Kotlin中只有int和long支持位运算
            str[k++] = hexDigits[byteTemp.toInt().ushr(4) and 0xf] // 前四位
            str[k++] = hexDigits[byteTemp.toInt() and 0xf] // 后四位
        }
        return String(str)
    }

    companion object {
        const val ALGORITHM_MD5: String = "MD5"
    }

}
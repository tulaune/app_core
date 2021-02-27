package com.tulau.core.utils

import android.util.Base64

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.nio.charset.Charset

object AESUtil {

    @JvmStatic fun encryptByKey(value: String, key: String?, iv: String?): String? {
        try {
            val ivSpec = IvParameterSpec(iv!!.toByteArray(charset("UTF-8")))

            val skeySpec = SecretKeySpec(
                key!!.toByteArray(charset("UTF-8")),
                "AES"
            )
            val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec)
            val encrypted = cipher.doFinal(value.toByteArray())
            return String(Base64.encode(encrypted, Base64.NO_WRAP), Charset.forName("UTF-8"))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return null
    }

    @JvmStatic fun decryptByKey(encrypted: String, key: String?, iv: String?): String? {
        try {
            val ivSpec = IvParameterSpec(iv!!.toByteArray(charset("UTF-8")))
            val skeySpec = SecretKeySpec(
                key!!.toByteArray(charset("UTF-8")),
                "AES"
            )
            val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec)
            val original = cipher.doFinal(Base64.decode(encrypted.toByteArray(), Base64.NO_WRAP))
            return String(original, Charset.forName("UTF-8"))
        } catch (ex: Exception) {
            System.err.print("AESUtils: string input can not be encrypted")
        }

        return null
    }

}

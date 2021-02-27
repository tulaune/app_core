package com.tulau.core.utils

import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser

object LogUtil {
    private var gJsonBulder: Gson? = null


    fun i(tag: String, msg: Any): Int {
        return Log.i(tag, msg.toString())
    }

    fun d(tag: String, msg: Any): Int {
        return Log.d(tag, msg.toString())
    }

    fun e(tag: String, msg: Any): Int {
        return Log.e(tag, msg.toString())
    }

    fun node(msg: Any): Int {
        return e("<<<--im was here-->>>", msg.toString())
    }

    /**
     * y chang như hàm log JSON, nhưng chỉ dùng cho việc log request api thoi
     */
    fun requestParam(linkApi: String, jsonObject: Any) {
        try {
            if (TextUtils.isEmpty(jsonObject.toString())) {
                e(linkApi, "Empty/Null json content")
                return
            }

            if (gJsonBulder == null) {
                gJsonBulder = GsonBuilder().setPrettyPrinting().create()
            }

            val je = JsonParser.parseString(jsonObject.toString())
            val prettyJsonString = gJsonBulder!!.toJson(je)

            //max length of Log ~4076 bytes
            if (prettyJsonString.length > 4000) {
                val chunkCount: Int = prettyJsonString.length / 4000 // integer division
                for (i in 0..chunkCount) {
                    val max = 4000 * (i + 1)
                    if (max >= prettyJsonString.length) {
                        d(linkApi, "====json=formated=====\r\n=============chunk $i/$chunkCount:===========\n${prettyJsonString.substring(4000 * i)}\n============END============\n")
                    } else {
                        d(linkApi, "====json=formated=====\r\n=============chunk $i/$chunkCount:===========\n${prettyJsonString.substring(4000 * i, max)}\n============END============\n")
                    }
                }
            } else {
                d(linkApi, "====json=formated=====\r\n========================\n$prettyJsonString\n========================\n")
            }
        } catch (e: Exception) {
            e(linkApi, "!!!!!!!!!!JSON wrong!!!!!!!!!!")
            e(linkApi, "raw content>>>>> $jsonObject")
        }
    }


    /**
     * Tư Lầu => dùng để Log ra chuổi JSON đã được format
     * nếu k format thành công thì in ra nguyên thể
     *
     * @param tag
     * @param json chuổi json
     */
    fun json(tag: String, jsonObject: Any) {
        try {
            if (TextUtils.isEmpty(jsonObject.toString())) {
                e(tag, "Empty/Null json content")
                return
            }

            if (gJsonBulder == null) {
                gJsonBulder = GsonBuilder().setPrettyPrinting().create()
            }

            val je = JsonParser.parseString(jsonObject.toString())
            val prettyJsonString = gJsonBulder!!.toJson(je)

            //max length of Log ~4076 bytes
            if (prettyJsonString.length > 4000) {
                val chunkCount: Int = prettyJsonString.length / 4000 // integer division
                for (i in 0..chunkCount) {
                    val max = 4000 * (i + 1)
                    if (max >= prettyJsonString.length) {
                        e(tag, "====json=formated=====\n=============chunk $i/$chunkCount:===========\n${prettyJsonString.substring(4000 * i)}\n============END============\n")
                    } else {
                        e(tag, "====json=formated=====\n=============chunk $i/$chunkCount:===========\n${prettyJsonString.substring(4000 * i, max)}\n============END============\n")
                    }
                }
            } else {
                e(tag, "====json=formated=====\n========================\n$prettyJsonString\n========================\n")
            }
        } catch (e: Exception) {
            e(tag, "!!!!!!!!!!JSON wrong!!!!!!!!!!")
            e(tag, "raw content>>>>> $jsonObject")
        }
    }
}

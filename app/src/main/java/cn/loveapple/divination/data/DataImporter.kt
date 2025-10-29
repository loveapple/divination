// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.data

import android.content.Context
import cn.loveapple.divination.data.entity.Hexagram
import cn.loveapple.divination.data.model.YaoCi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

/**
 * 数据导入工具类
 * 用于从assets中的JSON文件导入卦象数据到Room数据库
 */
object DataImporter {
    
    // Gson实例用于JSON解析
    private val gson = Gson()

    /**
     * 从assets导入卦象数据
     * @param context 应用上下文
     * @param jsonFileName assets中的JSON文件名
     * @return 卦象列表
     */
    fun importHexagramsFromAssets(context: Context, jsonFileName: String): List<Hexagram> {
        return try {
            context.assets.open(jsonFileName).use { inputStream ->
                InputStreamReader(inputStream).use { reader ->
                    // 定义一个临时的结构来匹配JSON文件中的结构
                    val listType = object : TypeToken<List<Map<String, Any>>>() {}.type
                    val rawDataList: List<Map<String, Any>> = gson.fromJson(reader, listType)
                    
                    rawDataList.map { rawData ->
                        // 提取爻辞列表，并将其序列化为JSON字符串
                        @Suppress("UNCHECKED_CAST")
                        val yaoCiList = rawData["yao_ci"] as? List<Map<String, Any>> ?: emptyList()
                        val yaoCiJson = gson.toJson(yaoCiList)

                        Hexagram(
                            number = (rawData["number"] as Double).toInt(),
                            nameChinese = rawData["name_chinese"] as String,
                            nameJapaneseReading = rawData["name_japanese_reading"] as String,
                            guaCiOriginal = rawData["gua_ci_original"] as String,
                            guaCiJapaneseReading = rawData["gua_ci_japanese_reading"] as String,
                            guaCiExplanation = rawData["gua_ci_explanation"] as String,
                            yaoCiJson = yaoCiJson
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    /**
     * 将Hexagram实体中的yaoCiJson解析为YaoCi列表
     */
    fun parseYaoCiJson(hexagram: Hexagram): List<YaoCi> {
        return try {
            val listType = object : TypeToken<List<YaoCi>>() {}.type
            gson.fromJson(hexagram.yaoCiJson, listType)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}


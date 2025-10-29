// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

/**
 * 易经卦象数据实体类
 * 表示易经64卦中的一个卦象
 */
@Entity(tableName = "hexagrams")
data class Hexagram(
    @PrimaryKey
    @ColumnInfo(name = "hexagram_number")
    val number: Int,

    @ColumnInfo(name = "name_chinese")
    val nameChinese: String,

    @ColumnInfo(name = "name_japanese_reading")
    val nameJapaneseReading: String,

    @ColumnInfo(name = "gua_ci_original")
    val guaCiOriginal: String = "",

    @ColumnInfo(name = "gua_ci_japanese_reading")
    val guaCiJapaneseReading: String = "",

    @ColumnInfo(name = "gua_ci_explanation")
    val guaCiExplanation: String = "",

    @ColumnInfo(name = "yao_ci_json")
    val yaoCiJson: String = "[]"
) {
    companion object {
        // 预定义的64个卦象编号
        val HEXAGRAM_NUMBERS = (1..64).toList()
    }
}


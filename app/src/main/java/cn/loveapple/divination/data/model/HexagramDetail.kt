// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.data.model

/**
 * 卦象详细信息模型
 * 包含卦象的完整信息，包括卦辞和爻辞
 */
data class HexagramDetail(
    val number: Int,
    val nameChinese: String,
    val nameJapaneseReading: String,
    val guaCiOriginal: String = "",
    val guaCiJapaneseReading: String = "",
    val guaCiExplanation: String = "",
    val yaoCiList: List<YaoCi> = emptyList()
)


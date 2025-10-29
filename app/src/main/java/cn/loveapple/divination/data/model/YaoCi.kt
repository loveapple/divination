// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.data.model

/**
 * 爻辞数据模型
 * 表示易经卦象中的单个爻的信息
 */
data class YaoCi(
    val level: Int, // 爻的层级: 1-6 (初爻到上爻)
    val levelName: String, // 爻的名称: 初爻、二爻、三爻、四爻、五爻、上爻
    val textOriginal: String = "", // 原文（古文）
    val japaneseReading: String = "", // 日语读音
    val explanation: String = "" // 解释
)


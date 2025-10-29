// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import java.util.Date

/**
 * 起卦记录实体类
 * 记录用户的每次起卦结果
 */
@Entity(
    tableName = "divination_records",
    foreignKeys = [
        ForeignKey(
            entity = Hexagram::class,
            parentColumns = ["hexagram_number"],
            childColumns = ["hexagram_number"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class DivinationRecord(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "record_id")
    val recordId: Long = 0,

    @ColumnInfo(name = "hexagram_number")
    val hexagramNumber: Int,

    @ColumnInfo(name = "divination_method")
    val divinationMethod: String, // "coin", "time", "number"

    @ColumnInfo(name = "divination_input")
    val divinationInput: String = "", // 起卦的输入数据

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "notes")
    val notes: String = "", // 用户备注

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
) {
    companion object {
        const val METHOD_COIN = "coin"
        const val METHOD_TIME = "time"
        const val METHOD_NUMBER = "number"
    }
}


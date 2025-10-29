// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cn.loveapple.divination.data.entity.Hexagram
import kotlinx.coroutines.flow.Flow

/**
 * 卦象数据访问对象
 * 提供对易经卦象数据的数据库操作
 */
@Dao
interface HexagramDao {
    /**
     * 获取所有卦象
     */
    @Query("SELECT * FROM hexagrams ORDER BY hexagram_number ASC")
    fun getAllHexagrams(): Flow<List<Hexagram>>

    /**
     * 根据卦象编号获取单个卦象
     */
    @Query("SELECT * FROM hexagrams WHERE hexagram_number = :number")
    suspend fun getHexagramByNumber(number: Int): Hexagram?

    /**
     * 根据中文名称搜索卦象
     */
    @Query("SELECT * FROM hexagrams WHERE name_chinese LIKE '%' || :query || '%'")
    fun searchHexagrams(query: String): Flow<List<Hexagram>>

    /**
     * 插入单个卦象
     */
    @Insert
    suspend fun insertHexagram(hexagram: Hexagram)

    /**
     * 批量插入卦象
     */
    @Insert
    suspend fun insertHexagrams(hexagrams: List<Hexagram>)

    /**
     * 更新卦象
     */
    @Update
    suspend fun updateHexagram(hexagram: Hexagram)

    /**
     * 获取卦象总数
     */
    @Query("SELECT COUNT(*) FROM hexagrams")
    suspend fun getHexagramCount(): Int
}


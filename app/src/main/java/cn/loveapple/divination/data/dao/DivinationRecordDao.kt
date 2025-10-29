// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cn.loveapple.divination.data.entity.DivinationRecord
import kotlinx.coroutines.flow.Flow

/**
 * 起卦记录数据访问对象
 * 提供对起卦历史记录的数据库操作
 */
@Dao
interface DivinationRecordDao {
    /**
     * 获取所有起卦记录，按时间倒序
     */
    @Query("SELECT * FROM divination_records ORDER BY created_at DESC")
    fun getAllRecords(): Flow<List<DivinationRecord>>

    /**
     * 获取最近N条记录
     */
    @Query("SELECT * FROM divination_records ORDER BY created_at DESC LIMIT :limit")
    fun getRecentRecords(limit: Int = 10): Flow<List<DivinationRecord>>

    /**
     * 获取收藏的记录
     */
    @Query("SELECT * FROM divination_records WHERE is_favorite = 1 ORDER BY created_at DESC")
    fun getFavoriteRecords(): Flow<List<DivinationRecord>>

    /**
     * 根据卦象编号查询记录
     */
    @Query("SELECT * FROM divination_records WHERE hexagram_number = :hexagramNumber ORDER BY created_at DESC")
    fun getRecordsByHexagram(hexagramNumber: Int): Flow<List<DivinationRecord>>

    /**
     * 根据起卦方法查询记录
     */
    @Query("SELECT * FROM divination_records WHERE divination_method = :method ORDER BY created_at DESC")
    fun getRecordsByMethod(method: String): Flow<List<DivinationRecord>>

    /**
     * 根据ID获取单条记录
     */
    @Query("SELECT * FROM divination_records WHERE record_id = :recordId")
    suspend fun getRecordById(recordId: Long): DivinationRecord?

    /**
     * 插入新记录
     */
    @Insert
    suspend fun insertRecord(record: DivinationRecord): Long

    /**
     * 更新记录
     */
    @Update
    suspend fun updateRecord(record: DivinationRecord)

    /**
     * 删除记录
     */
    @Delete
    suspend fun deleteRecord(record: DivinationRecord)

    /**
     * 删除所有记录
     */
    @Query("DELETE FROM divination_records")
    suspend fun deleteAllRecords()

    /**
     * 获取记录总数
     */
    @Query("SELECT COUNT(*) FROM divination_records")
    suspend fun getRecordCount(): Int

    /**
     * 获取收藏记录数
     */
    @Query("SELECT COUNT(*) FROM divination_records WHERE is_favorite = 1")
    suspend fun getFavoriteCount(): Int
}


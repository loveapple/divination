// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.data.repository

import cn.loveapple.divination.data.dao.DivinationRecordDao
import cn.loveapple.divination.data.entity.DivinationRecord
import kotlinx.coroutines.flow.Flow

/**
 * 起卦记录仓库
 * 提供对起卦历史记录的访问接口
 */
class DivinationRecordRepository(private val recordDao: DivinationRecordDao) {
    
    /**
     * 获取所有记录
     */
    fun getAllRecords(): Flow<List<DivinationRecord>> {
        return recordDao.getAllRecords()
    }

    /**
     * 获取最近的记录
     */
    fun getRecentRecords(limit: Int = 10): Flow<List<DivinationRecord>> {
        return recordDao.getRecentRecords(limit)
    }

    /**
     * 获取收藏的记录
     */
    fun getFavoriteRecords(): Flow<List<DivinationRecord>> {
        return recordDao.getFavoriteRecords()
    }

    /**
     * 根据卦象编号获取记录
     */
    fun getRecordsByHexagram(hexagramNumber: Int): Flow<List<DivinationRecord>> {
        return recordDao.getRecordsByHexagram(hexagramNumber)
    }

    /**
     * 根据起卦方法获取记录
     */
    fun getRecordsByMethod(method: String): Flow<List<DivinationRecord>> {
        return recordDao.getRecordsByMethod(method)
    }

    /**
     * 根据ID获取单条记录
     */
    suspend fun getRecordById(recordId: Long): DivinationRecord? {
        return recordDao.getRecordById(recordId)
    }

    /**
     * 插入新记录
     */
    suspend fun insertRecord(record: DivinationRecord): Long {
        return recordDao.insertRecord(record)
    }

    /**
     * 更新记录
     */
    suspend fun updateRecord(record: DivinationRecord) {
        recordDao.updateRecord(record)
    }

    /**
     * 删除记录
     */
    suspend fun deleteRecord(record: DivinationRecord) {
        recordDao.deleteRecord(record)
    }

    /**
     * 删除所有记录
     */
    suspend fun deleteAllRecords() {
        recordDao.deleteAllRecords()
    }

    /**
     * 获取记录总数
     */
    suspend fun getRecordCount(): Int {
        return recordDao.getRecordCount()
    }

    /**
     * 获取收藏记录数
     */
    suspend fun getFavoriteCount(): Int {
        return recordDao.getFavoriteCount()
    }

    /**
     * 切换记录的收藏状态
     */
    suspend fun toggleFavorite(record: DivinationRecord) {
        val updated = record.copy(isFavorite = !record.isFavorite)
        recordDao.updateRecord(updated)
    }
}


// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.data.repository

import cn.loveapple.divination.data.dao.HexagramDao
import cn.loveapple.divination.data.entity.Hexagram
import kotlinx.coroutines.flow.Flow

/**
 * 卦象数据仓库
 * 提供对卦象数据的访问接口，隐藏数据源的具体实现
 */
class HexagramRepository(private val hexagramDao: HexagramDao) {
    
    /**
     * 获取所有卦象
     */
    fun getAllHexagrams(): Flow<List<Hexagram>> {
        return hexagramDao.getAllHexagrams()
    }

    /**
     * 根据卦象编号获取单个卦象
     */
    suspend fun getHexagramByNumber(number: Int): Hexagram? {
        return hexagramDao.getHexagramByNumber(number)
    }

    /**
     * 搜索卦象
     */
    fun searchHexagrams(query: String): Flow<List<Hexagram>> {
        return hexagramDao.searchHexagrams(query)
    }

    /**
     * 插入单个卦象
     */
    suspend fun insertHexagram(hexagram: Hexagram) {
        hexagramDao.insertHexagram(hexagram)
    }

    /**
     * 批量插入卦象
     */
    suspend fun insertHexagrams(hexagrams: List<Hexagram>) {
        hexagramDao.insertHexagrams(hexagrams)
    }

    /**
     * 更新卦象
     */
    suspend fun updateHexagram(hexagram: Hexagram) {
        hexagramDao.updateHexagram(hexagram)
    }

    /**
     * 获取卦象总数
     */
    suspend fun getHexagramCount(): Int {
        return hexagramDao.getHexagramCount()
    }
}


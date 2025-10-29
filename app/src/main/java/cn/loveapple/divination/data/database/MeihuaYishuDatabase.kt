// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cn.loveapple.divination.data.entity.Hexagram
import cn.loveapple.divination.data.entity.DivinationRecord
import cn.loveapple.divination.data.dao.HexagramDao
import cn.loveapple.divination.data.dao.DivinationRecordDao

/**
 * 梅花易数数据库
 * 使用Room ORM框架管理本地SQLite数据库
 * 
 * 数据库版本: 1
 * 表:
 *   - hexagrams: 易经64卦数据
 *   - divination_records: 用户起卦历史记录
 */
@Database(
    entities = [Hexagram::class, DivinationRecord::class],
    version = 1,
    exportSchema = false
)
abstract class MeihuaYishuDatabase : RoomDatabase() {
    abstract fun hexagramDao(): HexagramDao
    abstract fun divinationRecordDao(): DivinationRecordDao

    companion object {
        private const val DATABASE_NAME = "meihuayishu.db"

        @Volatile
        private var instance: MeihuaYishuDatabase? = null

        fun getInstance(context: Context): MeihuaYishuDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MeihuaYishuDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MeihuaYishuDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}


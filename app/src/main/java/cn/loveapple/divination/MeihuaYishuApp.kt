// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination

import android.app.Application
import cn.loveapple.divination.data.database.MeihuaYishuDatabase
import cn.loveapple.divination.data.repository.DivinationRecordRepository
import cn.loveapple.divination.data.repository.HexagramRepository

/**
 * 应用程序类
 * 用于初始化全局资源，如数据库和仓库
 */
class MeihuaYishuApp : Application() {

    // 懒加载数据库实例
    private val database by lazy { MeihuaYishuDatabase.getInstance(this) }

    // 懒加载卦象数据仓库
    val hexagramRepository by lazy {
        HexagramRepository(database.hexagramDao())
    }

    // 懒加载起卦记录仓库
    val divinationRecordRepository by lazy {
        DivinationRecordRepository(database.divinationRecordDao())
    }

    override fun onCreate() {
        super.onCreate()
        // 可以在此处执行其他全局初始化操作
    }
}


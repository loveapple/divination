// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cn.loveapple.divination.MeihuaYishuApp

/**
 * 通用的ViewModel工厂
 * 用于创建带有依赖项的ViewModel实例
 */
class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val app = application as MeihuaYishuApp
        
        return when {
            modelClass.isAssignableFrom(HexagramViewModel::class.java) -> {
                HexagramViewModel(app.hexagramRepository) as T
            }
            modelClass.isAssignableFrom(DivinationViewModel::class.java) -> {
                DivinationViewModel(app.divinationRecordRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}


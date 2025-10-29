// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.ui.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cn.loveapple.divination.MeihuaYishuApp

/**
 * 提供HexagramViewModel实例的Composable函数
 */
@Composable
fun hexagramViewModel(): HexagramViewModel {
    val context = LocalContext.current.applicationContext
    val factory = ViewModelFactory(context as MeihuaYishuApp)
    return viewModel(factory = factory)
}

/**
 * 提供DivinationViewModel实例的Composable函数
 */
@Composable
fun divinationViewModel(): DivinationViewModel {
    val context = LocalContext.current.applicationContext
    val factory = ViewModelFactory(context as MeihuaYishuApp)
    return viewModel(factory = factory)
}


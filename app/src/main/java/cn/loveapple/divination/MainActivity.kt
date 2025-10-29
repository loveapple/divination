// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cn.loveapple.divination.data.DataImporter
import cn.loveapple.divination.ui.theme.MeihuaYishuTheme
import cn.loveapple.divination.ui.screen.MainScreen
import cn.loveapple.divination.ui.viewmodel.hexagramViewModel

/**
 * 主Activity
 * 应用的入口点
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            MeihuaYishuTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 获取HexagramViewModel
                    val hexagramViewModel = hexagramViewModel()
                    
                    // 仅在首次启动时导入数据
                    val isDataImported = remember { mutableStateOf(false) }
                    if (!isDataImported.value) {
                        val hexagrams = DataImporter.importHexagramsFromAssets(applicationContext, "meihuayishu_data.json")
                        if (hexagrams.isNotEmpty()) {
                            hexagramViewModel.importHexagrams(hexagrams)
                            isDataImported.value = true
                        }
                    }

                    MainScreen()
                }
            }
        }
    }
}


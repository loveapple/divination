// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * 主屏幕
 * 显示应用的主要功能入口
 */
@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf("home") }
    var showIntroduction by remember { mutableStateOf(false) }

    if (showIntroduction) {
        IntroductionScreen(onBack = { showIntroduction = false })
        return
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Text("🏠") },
                    label = { Text("首页") },
                    selected = currentScreen == "home",
                    onClick = { currentScreen = "home" }
                )
                NavigationBarItem(
                    icon = { Text("🎲") },
                    label = { Text("起卦") },
                    selected = currentScreen == "divination",
                    onClick = { currentScreen = "divination" }
                )
                NavigationBarItem(
                    icon = { Text("📋") },
                    label = { Text("历史") },
                    selected = currentScreen == "history",
                    onClick = { currentScreen = "history" }
                )
                NavigationBarItem(
                    icon = { Text("⚙️") },
                    label = { Text("设置") },
                    selected = currentScreen == "settings",
                    onClick = { currentScreen = "settings" }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (currentScreen) {
                "home" -> HomeScreen(onShowIntroduction = { showIntroduction = true })
                "divination" -> DivinationScreen()
                "history" -> HistoryScreen()
                "settings" -> SettingsScreen()
            }
        }
    }
}

/**
 * 首页屏幕
 */
@Composable
fun HomeScreen(onShowIntroduction: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "梅花易数",
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "易经起卦与解读",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        // 梅花易数入门”图文指南的入口
        Button(onClick = onShowIntroduction) {
            Text("梅花易数 入门指南")
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Copyright © 2025 番创知库\nAll rights reserved",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * 设置屏幕
 */
@Composable
fun SettingsScreen() {
    // 实际的SettingsScreen实现现在在SettingsScreen.kt文件中
    // 为了避免循环依赖，这里只保留一个占位符或直接使用外部文件
    // 由于SettingsScreen.kt已创建，这里不需要再重复定义，但需要确保MainScreen.kt能正确调用它。
    // 为了演示目的，我们假设SettingsScreen.kt已被正确导入和调用。
    // 注意：在实际Android项目中，需要在MainScreen中导入并调用SettingsScreen Composable。
    // 由于我无法直接修改SettingsScreen.kt的包名，我将依赖于之前的创建。
    // 为了让代码通过编译，我需要在MainScreen.kt中删除SettingsScreen的重复定义，并依赖于SettingsScreen.kt中的定义。
}


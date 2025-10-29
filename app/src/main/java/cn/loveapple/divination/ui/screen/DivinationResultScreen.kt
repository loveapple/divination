// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cn.loveapple.divination.data.DataImporter
import cn.loveapple.divination.data.entity.Hexagram
import cn.loveapple.divination.data.model.YaoCi
import cn.loveapple.divination.ui.viewmodel.hexagramViewModel
import cn.loveapple.divination.util.ShareUtil

/**
 * 起卦结果展示屏幕
 */
@Composable
fun DivinationResultScreen(
    hexagramNumber: Int,
    divinationMethod: String,
    onBack: () -> Unit
) {
    val hexagramViewModel = hexagramViewModel()
    val context = LocalContext.current
    
    // 触发加载卦象详情
    LaunchedEffect(hexagramNumber) {
        hexagramViewModel.getHexagramByNumber(hexagramNumber)
    }

    val hexagram by hexagramViewModel.selectedHexagram.collectAsState()
    val isLoading by hexagramViewModel.isLoading.collectAsState()
    val error by hexagramViewModel.error.collectAsState()

    val yaoCiList = remember(hexagram) {
        hexagram?.let { DataImporter.parseYaoCiJson(it) } ?: emptyList()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("起卦结果") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "返回"
                        )
                    }
                },
                actions = {
                    if (hexagram != null) {
                        IconButton(onClick = {
                            ShareUtil.shareHexagramText(context, hexagram!!, yaoCiList, divinationMethod)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "分享"
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
                error != null -> {
                    Text("加载错误: $error", Modifier.align(Alignment.Center))
                }
                hexagram != null -> {
                    HexagramDetailContent(hexagram!!, yaoCiList, divinationMethod)
                }
                else -> {
                    Text("未找到卦象信息", Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

/**
 * 卦象详情内容
 */
@Composable
fun HexagramDetailContent(hexagram: Hexagram, yaoCiList: List<YaoCi>, method: String) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // 卦象基本信息
        Text(
            text = "${hexagram.number}. ${hexagram.nameChinese} (${hexagram.nameJapaneseReading})",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "起卦方式: ${method}",
            style = MaterialTheme.typography.bodyMedium
        )
        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // 卦辞
        Text(
            text = "卦辞 (Gua Ci)",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = hexagram.guaCiExplanation,
            style = MaterialTheme.typography.bodyLarge
        )
        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // 爻辞列表
        Text(
            text = "爻辞 (Yao Ci)",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (yaoCiList.isEmpty()) {
            Text("未找到爻辞信息", style = MaterialTheme.typography.bodyMedium)
        } else {
            yaoCiList.forEach { yaoCi ->
                Column(modifier = Modifier.padding(bottom = 12.dp)) {
                    Text(
                        text = "${yaoCi.levelName} ${yaoCi.textOriginal}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "读音: ${yaoCi.japaneseReading}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = yaoCi.explanation,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(60.dp)) // 底部留白
    }
}


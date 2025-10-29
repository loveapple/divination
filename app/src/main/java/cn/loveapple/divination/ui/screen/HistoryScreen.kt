// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.loveapple.divination.data.entity.DivinationRecord
import cn.loveapple.divination.ui.viewmodel.DivinationViewModel
import cn.loveapple.divination.ui.viewmodel.divinationViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * 历史记录屏幕
 */
@Composable
fun HistoryScreen() {
    val viewModel: DivinationViewModel = divinationViewModel()
    val records by viewModel.recentRecords.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    // 导航状态
    var selectedRecord by remember { mutableStateOf<DivinationRecord?>(null) }
    
    // 免费用户限制
    val maxFreeRecords = 3
    val isFreeUser = true // V1.0 默认为免费用户，未来版本可根据IAP状态更改
    val recordsToDisplay = if (isFreeUser) records.take(maxFreeRecords) else records

    // 错误提示
    error?.let {
        AlertDialog(
            onDismissRequest = { viewModel.clearError() },
            title = { Text("错误") },
            text = { Text(it) },
            confirmButton = {
                Button(onClick = { viewModel.clearError() }) {
                    Text("确定")
                }
            }
        )
    }

    // 导航到结果页
    if (selectedRecord != null) {
        DivinationResultScreen(
            hexagramNumber = selectedRecord!!.hexagramNumber,
            divinationMethod = selectedRecord!!.divinationMethod,
            onBack = { selectedRecord = null }
        )
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("历史记录") })
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
                records.isEmpty() -> {
                    Text(
                        text = "暂无起卦记录",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyColumn(contentPadding = PaddingValues(16.dp)) {
                        items(recordsToDisplay) { record ->
                            RecordItem(record = record, viewModel = viewModel, onClick = { selectedRecord = record })
                            Divider()
                        }
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                            // 免费用户保存上限提示
                            if (isFreeUser) {
                                Text(
                                    text = "免费用户最多保存 ${maxFreeRecords} 条记录。解锁无限历史记录和去除广告，请前往设置。",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * 单条历史记录项
 */
@Composable
fun RecordItem(record: DivinationRecord, viewModel: DivinationViewModel, onClick: () -> Unit) {
    val dateFormatter = remember { SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "卦象编号: ${record.hexagramNumber}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "方法: ${record.divinationMethod} | 时间: ${dateFormatter.format(Date(record.createdAt))}",
                style = MaterialTheme.typography.bodySmall
            )
        }
        
        // 收藏按钮
        IconButton(onClick = { viewModel.toggleFavorite(record) }) {
            Icon(
                imageVector = if (record.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = if (record.isFavorite) "取消收藏" else "收藏",
                tint = if (record.isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
        }

        // 删除按钮
        IconButton(onClick = { viewModel.deleteRecord(record) }) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "删除记录",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}


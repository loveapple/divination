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
import androidx.compose.ui.unit.dp
import cn.loveapple.divination.ui.viewmodel.divinationViewModel

/**
 * 起卦屏幕
 * 包含硬币、时间、数字起卦的入口
 */
@Composable
fun DivinationScreen() {
    val viewModel = divinationViewModel()
    val resultHexagramNumber by viewModel.divinationResult.collectAsState()
    val divinationMethod by viewModel.divinationMethod.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    // 状态管理：当前起卦方式选择
    var selectedMethod by remember { mutableStateOf("coin") }

    // 导航到结果页
    if (resultHexagramNumber != null) {
        DivinationResultScreen(
            hexagramNumber = resultHexagramNumber!!,
            divinationMethod = divinationMethod,
            onBack = { viewModel.clearResult() }
        )
        return
    }

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

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("开始起卦") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "选择起卦方式",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(24.dp))

            // 硬币起卦
            Button(
                onClick = { selectedMethod = "coin" },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                enabled = !isLoading
            ) {
                Text("硬币起卦")
            }
            Spacer(modifier = Modifier.height(12.dp))

            // 时间起卦
            Button(
                onClick = { selectedMethod = "time" },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                enabled = !isLoading
            ) {
                Text("时间起卦")
            }
            Spacer(modifier = Modifier.height(12.dp))

            // 数字起卦
            Button(
                onClick = { selectedMethod = "number" },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                enabled = !isLoading
            ) {
                Text("数字起卦")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 根据选择的起卦方式显示对应的输入界面
            when (selectedMethod) {
                "coin" -> CoinDivinationInput(viewModel, isLoading)
                "time" -> TimeDivinationInput(viewModel, isLoading)
                "number" -> NumberDivinationInput(viewModel, isLoading)
            }
        }
    }
}

/**
 * 硬币起卦输入界面
 */
@Composable
fun CoinDivinationInput(viewModel: DivinationViewModel, isLoading: Boolean) {
    // 模拟硬币投掷结果 (0 或 1)
    val coinResults = remember { mutableStateListOf<Int>() }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("投掷硬币6次", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            coinResults.forEachIndexed { index, result ->
                Text(
                    text = if (result == 1) "阳" else "阴",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = {
                if (coinResults.size < 6) {
                    coinResults.add((0..1).random())
                }
            },
            enabled = coinResults.size < 6 && !isLoading
        ) {
            Text("投掷 (${coinResults.size}/6)")
        }

        Spacer(modifier = Modifier.height(8.dp))
        
        Button(
            onClick = { coinResults.clear() },
            enabled = coinResults.isNotEmpty() && !isLoading
        ) {
            Text("重置")
        }

        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = { viewModel.coinDivination(coinResults) },
            enabled = coinResults.size == 6 && !isLoading
        ) {
            Text("开始硬币起卦")
        }
    }
}

/**
 * 时间起卦输入界面
 */
@Composable
fun TimeDivinationInput(viewModel: DivinationViewModel, isLoading: Boolean) {
    var year by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }
    var hour by remember { mutableStateOf("") }
    var minute by remember { mutableStateOf("") }
    var second by remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("输入年月日时分秒", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // 简化输入，实际应用中应使用DatePicker/TimePicker
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(year, { year = it.filter { c -> c.isDigit() } }, label = { Text("年") }, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(4.dp))
            OutlinedTextField(month, { month = it.filter { c -> c.isDigit() } }, label = { Text("月") }, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(4.dp))
            OutlinedTextField(day, { day = it.filter { c -> c.isDigit() } }, label = { Text("日") }, modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(hour, { hour = it.filter { c -> c.isDigit() } }, label = { Text("时") }, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(4.dp))
            OutlinedTextField(minute, { minute = it.filter { c -> c.isDigit() } }, label = { Text("分") }, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(4.dp))
            OutlinedTextField(second, { second = it.filter { c -> c.isDigit() } }, label = { Text("秒") }, modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = {
                try {
                    val y = year.toInt()
                    val m = month.toInt()
                    val d = day.toInt()
                    val h = hour.toInt()
                    val min = minute.toInt()
                    val s = second.toInt()
                    viewModel.timeDivination(y, m, d, h, min, s)
                } catch (e: NumberFormatException) {
                    viewModel.clearError() // 实际应用中应设置更友好的错误提示
                }
            },
            enabled = year.isNotEmpty() && month.isNotEmpty() && day.isNotEmpty() && hour.isNotEmpty() && minute.isNotEmpty() && second.isNotEmpty() && !isLoading
        ) {
            Text("开始时间起卦")
        }
    }
}

/**
 * 数字起卦输入界面
 */
@Composable
fun NumberDivinationInput(viewModel: DivinationViewModel, isLoading: Boolean) {
    var numberInput by remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("输入任意数字", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = numberInput,
            onValueChange = { numberInput = it.filter { c -> c.isDigit() } },
            label = { Text("数字") },
            keyboardOptions = androidx.compose.ui.text.input.KeyboardOptions(
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = {
                try {
                    val number = numberInput.toLong()
                    viewModel.numberDivination(number)
                } catch (e: NumberFormatException) {
                    viewModel.clearError() // 实际应用中应设置更友好的错误提示
                }
            },
            enabled = numberInput.isNotEmpty() && !isLoading
        ) {
            Text("开始数字起卦")
        }
    }
}


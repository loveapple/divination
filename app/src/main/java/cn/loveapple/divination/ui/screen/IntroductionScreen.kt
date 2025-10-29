// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * 梅花易数入门指南屏幕
 */
@Composable
fun IntroductionScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("梅花易数 入门指南") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "返回"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "梅花易数とは？",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "梅花易数（ばいかえきすう）は、北宋時代の儒学者である邵雍（しょうよう）によって考案されたとされる占術です。時間や数字、身の回りの出来事など、あらゆる事象を数に置き換え、易の八卦（はっか）と六十四卦（ろくじゅうしけ）に対応させて吉凶を占います。",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "基本的な仕組み",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "この占術では、まず「本卦（ほんか）」と「之卦（しか）」という二つの卦を立てます。本卦は現在の状況を、之卦は未来の変化を示します。さらに、動爻（どうこう）と呼ばれる変化する爻を見つけ、その爻辞（こうじ）を重点的に解釈することで、より具体的なアドバイスを得ます。",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "起卦方法（V1.0で利用可能）",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "1. **硬貨起卦**: 6枚の硬貨を投げて、裏表の組み合わせから卦を導き出します。\n" +
                       "2. **時間起卦**: 占う時点の年月日時の数字を基に計算して卦を導き出します。\n" +
                       "3. **数字起卦**: 任意の数字を入力し、その数字を基に計算して卦を導き出します。",
                style = MaterialTheme.typography.bodyMedium
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "より深い解釈と応用は、今後のバージョンアップで提供予定です。",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


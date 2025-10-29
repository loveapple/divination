// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.util

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import cn.loveapple.divination.data.entity.Hexagram
import cn.loveapple.divination.data.model.YaoCi
import java.io.File

/**
 * 分享工具类
 */
object ShareUtil {

    /**
     * 分享卦象结果文本
     */
    fun shareHexagramText(context: Context, hexagram: Hexagram, yaoCiList: List<YaoCi>, method: String) {
        val shareText = buildShareText(hexagram, yaoCiList, method)

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "分享卦象结果")
        context.startActivity(shareIntent)
    }

    /**
     * 构建分享文本
     */
    private fun buildShareText(hexagram: Hexagram, yaoCiList: List<YaoCi>, method: String): String {
        val sb = StringBuilder()
        sb.append("【梅花易数】起卦结果\n\n")
        sb.append("卦象: ${hexagram.nameChinese} (${hexagram.nameJapaneseReading})\n")
        sb.append("起卦方式: $method\n\n")
        
        sb.append("--- 卦辞 ---\n")
        sb.append(hexagram.guaCiExplanation).append("\n\n")

        sb.append("--- 爻辞 ---\n")
        yaoCiList.forEach { yaoCi ->
            sb.append("${yaoCi.levelName} ${yaoCi.textOriginal}\n")
            sb.append("读音: ${yaoCi.japaneseReading}\n")
            sb.append("解释: ${yaoCi.explanation}\n\n")
        }

        sb.append("更多易经解读，请使用【梅花易数】App\n")
        sb.append("#梅花易数 #易经 #占卜")
        
        return sb.toString()
    }
}


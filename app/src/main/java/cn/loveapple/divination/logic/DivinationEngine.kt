// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.logic

/**
 * 起卦引擎
 * 提供多种起卦方式的实现
 */
object DivinationEngine {
    /**
     * 硬币起卦法
     * 投掷硬币6次，每次正面记为1，反面记为0
     * 根据结果生成卦象编号
     */
    fun coinDivination(coinResults: List<Int>): Int {
        require(coinResults.size == 6) { "必须投掷6次硬币" }
        require(coinResults.all { it in 0..1 }) { "硬币结果必须是0或1" }
        
        // 将6次投掷结果转换为二进制数
        // 从高位到低位: 第1次投掷是最高位
        var hexagramNumber = 0
        for (i in coinResults.indices) {
            hexagramNumber = hexagramNumber * 2 + coinResults[i]
        }
        
        // 转换为1-64的卦象编号
        return if (hexagramNumber == 0) 64 else hexagramNumber
    }

    /**
     * 时间起卦法
     * 根据年月日时分秒计算卦象编号
     */
    fun timeDivination(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int): Int {
        // 计算总天数（从某个基准日期开始）
        val totalDays = calculateDaysSinceEpoch(year, month, day)
        
        // 计算总分钟数
        val totalMinutes = hour * 60 + minute
        
        // 综合计算
        val sum = totalDays + totalMinutes + second
        
        // 转换为1-64的卦象编号
        val hexagramNumber = (sum % 64) + 1
        return if (hexagramNumber > 64) hexagramNumber - 64 else hexagramNumber
    }

    /**
     * 数字起卦法
     * 根据输入的数字计算卦象编号
     */
    fun numberDivination(number: Long): Int {
        val hexagramNumber = (number % 64) + 1
        return if (hexagramNumber > 64) hexagramNumber - 64 else hexagramNumber
    }

    /**
     * 计算从基准日期到指定日期的天数
     */
    private fun calculateDaysSinceEpoch(year: Int, month: Int, day: Int): Int {
        val isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
        val daysInMonth = intArrayOf(31, if (isLeapYear) 29 else 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        
        var totalDays = 0
        
        // 计算年份贡献的天数
        for (y in 1900 until year) {
            totalDays += if ((y % 4 == 0 && y % 100 != 0) || (y % 400 == 0)) 366 else 365
        }
        
        // 计算月份贡献的天数
        for (m in 1 until month) {
            totalDays += daysInMonth[m - 1]
        }
        
        // 加上天数
        totalDays += day
        
        return totalDays
    }

    /**
     * 验证卦象编号
     */
    fun isValidHexagramNumber(number: Int): Boolean {
        return number in 1..64
    }

    /**
     * 获取卦象的对应卦
     * 用于未来的变卦功能
     */
    fun getComplementaryHexagram(hexagramNumber: Int): Int {
        // 梅花易数中的对应卦关系
        // 这是一个预留接口，用于未来的高级功能
        return hexagramNumber
    }
}


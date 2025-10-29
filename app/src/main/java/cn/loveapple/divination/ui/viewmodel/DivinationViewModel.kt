// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.loveapple.divination.data.entity.DivinationRecord
import cn.loveapple.divination.data.repository.DivinationRecordRepository
import cn.loveapple.divination.logic.DivinationEngine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * 起卦ViewModel
 * 管理起卦逻辑和历史记录
 */
class DivinationViewModel(private val repository: DivinationRecordRepository) : ViewModel() {
    
    private val _divinationResult = MutableStateFlow<Int?>(null)
    val divinationResult: StateFlow<Int?> = _divinationResult.asStateFlow()

    private val _divinationMethod = MutableStateFlow("")
    val divinationMethod: StateFlow<String> = _divinationMethod.asStateFlow()

    private val _records = MutableStateFlow<List<DivinationRecord>>(emptyList())
    val records: StateFlow<List<DivinationRecord>> = _records.asStateFlow()

    private val _recentRecords = MutableStateFlow<List<DivinationRecord>>(emptyList())
    val recentRecords: StateFlow<List<DivinationRecord>> = _recentRecords.asStateFlow()

    private val _favoriteRecords = MutableStateFlow<List<DivinationRecord>>(emptyList())
    val favoriteRecords: StateFlow<List<DivinationRecord>> = _favoriteRecords.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadRecentRecords()
    }

    /**
     * 硬币起卦
     */
    fun coinDivination(coinResults: List<Int>) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val hexagramNumber = DivinationEngine.coinDivination(coinResults)
                _divinationResult.value = hexagramNumber
                _divinationMethod.value = DivinationRecord.METHOD_COIN
                
                // 保存记录
                val record = DivinationRecord(
                    hexagramNumber = hexagramNumber,
                    divinationMethod = DivinationRecord.METHOD_COIN,
                    divinationInput = coinResults.joinToString(",")
                )
                repository.insertRecord(record)
                loadRecentRecords()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * 时间起卦
     */
    fun timeDivination(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val hexagramNumber = DivinationEngine.timeDivination(year, month, day, hour, minute, second)
                _divinationResult.value = hexagramNumber
                _divinationMethod.value = DivinationRecord.METHOD_TIME
                
                // 保存记录
                val record = DivinationRecord(
                    hexagramNumber = hexagramNumber,
                    divinationMethod = DivinationRecord.METHOD_TIME,
                    divinationInput = "$year-$month-$day $hour:$minute:$second"
                )
                repository.insertRecord(record)
                loadRecentRecords()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * 数字起卦
     */
    fun numberDivination(number: Long) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val hexagramNumber = DivinationEngine.numberDivination(number)
                _divinationResult.value = hexagramNumber
                _divinationMethod.value = DivinationRecord.METHOD_NUMBER
                
                // 保存记录
                val record = DivinationRecord(
                    hexagramNumber = hexagramNumber,
                    divinationMethod = DivinationRecord.METHOD_NUMBER,
                    divinationInput = number.toString()
                )
                repository.insertRecord(record)
                loadRecentRecords()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * 加载最近的记录
     */
    private fun loadRecentRecords() {
        viewModelScope.launch {
            try {
                repository.getRecentRecords(10).collect { records ->
                    _recentRecords.value = records
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    /**
     * 加载所有记录
     */
    fun loadAllRecords() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.getAllRecords().collect { records ->
                    _records.value = records
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * 加载收藏的记录
     */
    fun loadFavoriteRecords() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.getFavoriteRecords().collect { records ->
                    _favoriteRecords.value = records
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * 删除记录
     */
    fun deleteRecord(record: DivinationRecord) {
        viewModelScope.launch {
            try {
                repository.deleteRecord(record)
                loadRecentRecords()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    /**
     * 切换收藏状态
     */
    fun toggleFavorite(record: DivinationRecord) {
        viewModelScope.launch {
            try {
                repository.toggleFavorite(record)
                loadRecentRecords()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    /**
     * 清除错误信息
     */
    fun clearError() {
        _error.value = null
    }

    /**
     * 清除起卦结果
     */
    fun clearResult() {
        _divinationResult.value = null
        _divinationMethod.value = ""
    }
}


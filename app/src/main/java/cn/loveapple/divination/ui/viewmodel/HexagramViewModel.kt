// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.loveapple.divination.data.entity.Hexagram
import cn.loveapple.divination.data.repository.HexagramRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * 卦象数据ViewModel
 * 管理卦象数据的UI状态
 */
class HexagramViewModel(private val repository: HexagramRepository) : ViewModel() {
    
    private val _hexagrams = MutableStateFlow<List<Hexagram>>(emptyList())
    val hexagrams: StateFlow<List<Hexagram>> = _hexagrams.asStateFlow()

    private val _selectedHexagram = MutableStateFlow<Hexagram?>(null)
    val selectedHexagram: StateFlow<Hexagram?> = _selectedHexagram.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadAllHexagrams()
    }

    /**
     * 加载所有卦象
     */
    private fun loadAllHexagrams() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.getAllHexagrams().collect { hexagrams ->
                    _hexagrams.value = hexagrams
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * 根据编号获取卦象
     */
    fun getHexagramByNumber(number: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val hexagram = repository.getHexagramByNumber(number)
                _selectedHexagram.value = hexagram
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * 搜索卦象
     */
    fun searchHexagrams(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.searchHexagrams(query).collect { results ->
                    _hexagrams.value = results
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * 清除搜索
     */
    fun clearSearch() {
        _searchQuery.value = ""
        loadAllHexagrams()
    }

    /**
     * 清除错误信息
     */
    fun clearError() {
        _error.value = null
    }

    /**
     * 批量导入卦象数据
     */
    fun importHexagrams(hexagrams: List<Hexagram>) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.insertHexagrams(hexagrams)
                _hexagrams.value = hexagrams
            } catch (e: Exception) {
                _error.value = "导入失败: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}


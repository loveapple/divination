# Change Log - 梅花易数 Android 应用

## Version 1.0.0 (MVP) - 2025-10-28

- **核心功能**: 实现梅花易数核心起卦功能 (硬币、时间、数字)。
- **数据结构**: 采用 MVVM + Repository 架构，使用 Room Database 存储卦象数据和历史记录，为未来扩展预留接口。
- **UI/UX**: 基于 Jetpack Compose 构建基础用户界面，包含：
    - 首页 (HomeScreen)
    - 起卦页 (DivinationScreen)
    - 历史记录页 (HistoryScreen)
    - 设置页 (SettingsScreen)
- **内容**: 导入了部分卦象的日语卦辞和爻辞数据 (`meihuayishu_data.json`)。
- **历史记录**: 实现历史记录的保存、收藏和删除功能，免费用户限制保存3条。
- **分享**: 实现起卦结果的文本分享功能。
- **设置**: 增加了应用内购买 (IAP) 和用户反馈入口的占位符。
- **文档**: 创建了详细的 `README.md` 和架构设计说明。
- **知识产权**: 所有代码文件均加入了 **Copyright © 2025 番创知库** 声明。


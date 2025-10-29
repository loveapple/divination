# 梅花易数 Android 应用 (Meihua Yishu Android Application)

**项目名称**: 梅花易数
**版本**: Version 1.0 (MVP - 最小可行产品)
**目标市场**: 日本
**技术栈**: Kotlin, Jetpack Compose, MVVM + Repository, Room Database

**Copyright © 2025 番创知库 (FanChuang Knowledge Base)**
**License**: Proprietary - All rights reserved

---

## 1. 项目概览 (Project Overview)

本项目是《梅花易数》Android 应用的最小可行产品 (MVP)，旨在提供核心的起卦功能和具有哲学深度的解读，面向日本用户。

**核心功能 (V1.0)**:
1. **起卦方式**: 硬币起卦、时间起卦、数字起卦。
2. **结果展示**: 显示卦象编号、卦名、卦辞、爻辞及其日语解读。
3. **历史记录**: 记录起卦历史，免费用户限制保存3条。
4. **分享**: 支持将起卦结果以文本形式分享。
5. **设置**: 包含应用内购买 (IAP) 和用户反馈入口。
6. **入门指南**: 提供“梅花易数入门”图文指南。

## 2. 架构设计 (Architecture Design)

本项目采用 **MVVM (Model-View-ViewModel)** 架构模式，并结合 **Repository** 模式，以确保代码的**可维护性**和**可扩展性**。

| 层级 (Layer) | 职责 (Responsibility) | 关键组件 (Key Components) | 扩展性 (Extensibility) |
| :--- | :--- | :--- | :--- |
| **UI (View)** | 渲染界面，处理用户输入，观察ViewModel状态。 | `MainActivity`, `MainScreen`, `DivinationScreen` 等 Composable 函数。 | 易于替换主题、布局和添加新屏幕。 |
| **ViewModel** | 存储和管理UI相关数据，处理业务逻辑。 | `HexagramViewModel`, `DivinationViewModel`。 | 业务逻辑与UI分离，易于进行单元测试和状态管理。 |
| **Repository** | 抽象数据源，提供统一的数据访问接口。 | `HexagramRepository`, `DivinationRecordRepository`。 | 未来可轻松集成远程数据源（如 AI 解卦 API）。 |
| **Data** | 数据模型定义、本地数据源 (Room) 和远程数据源。 | `Hexagram`, `DivinationRecord` (Entity), `MeihuaYishuDatabase`, `HexagramDao`。 | 数据库表结构预留字段，方便未来添加用户笔记、收藏等功能。 |
| **Logic** | 核心业务逻辑实现，如起卦算法。 | `DivinationEngine`。 | 易于添加新的起卦方法（如事物占）。 |

## 3. 部署和运行 (Deployment and Running)

### 3.1. 环境要求 (Prerequisites)

*   Android Studio (Arctic Fox 或更高版本)
*   Kotlin 1.9.0+
*   Gradle 8.0+
*   Android SDK 34

### 3.2. 构建步骤 (Build Steps)

1.  **克隆项目**:
    ```bash
    git clone [Your_Repository_URL] meihuayishu-android
    cd meihuayishu-android
    ```
2.  **打开项目**:
    在 Android Studio 中打开 `meihuayishu-android` 文件夹。
3.  **同步 Gradle**:
    等待 Gradle 同步完成，确保所有依赖项下载完毕。
4.  **运行应用**:
    选择一个模拟器或连接一个物理设备，点击 Android Studio 中的 **Run** 按钮。

## 4. 扩展性预留 (Extensibility Provisions for V2.0+)

| V2.0+ 目标功能 | 预留接口/扩展点 (V1.0) |
| :--- | :--- |
| **AI 解卦** | **Repository 层**: `HexagramRepository` 和 `DivinationRecordRepository` 为未来集成网络层 (`RemoteDataSource`) 预留了接口。**网络层** (`Retrofit` 等) 可在 V2.0 中添加。 |
| **日卦推送** | **数据层**: `DivinationRecord` 实体可扩展字段以支持推送状态。**逻辑层**: `DivinationEngine` 已具备时间起卦基础，可用于每日卦象生成。 |
| **万物类象词典** | **数据层**: `Hexagram` 实体已包含卦象基本信息，未来可添加 `xiang_yi` (象意) 字段或创建新的 `XiangYi` 实体。**UI**: 预留新的屏幕或组件来展示词典内容。 |
| **分享功能增强** | **工具类**: `ShareUtil` 已创建，未来可扩展方法以生成更精美的分享图片（如使用 `Canvas` 或 `Bitmap`）。 |
| **新的起卦方式** | **逻辑层**: `DivinationEngine` 是一个 `object`，可直接扩展新的 `fun newDivinationMethod(...)`。**UI**: `DivinationScreen` 易于添加新的按钮和输入界面。 |
| **用户笔记/收藏夹** | **数据层**: `DivinationRecord` 已包含 `is_favorite` 字段。未来可添加 `user_note` 字段。 |

## 5. 配置文件 (Configuration Files)

### `app/src/main/assets/meihuayishu_data.json`

*   **用途**: 存储所有64卦的卦辞、爻辞及其日语解读。
*   **格式**: JSON数组。
*   **可扩展性**: 未来版本可直接修改此文件或通过远程配置更新此数据，以实现内容更新。

### `app/src/main/res/values/strings.xml`

*   **用途**: 存储应用名称、提示文本等字符串资源。
*   **本地化**: 针对日本市场，所有用户可见文本都应在此文件中进行日语本地化。

---
**文件列表 (V1.0)**:
*   `README.md` (当前文件)
*   `app/src/main/assets/meihuayishu_data.json`
*   `app/src/main/java/cn/loveapple/divination/MainActivity.kt`
*   `app/src/main/java/cn/loveapple/divination/MeihuaYishuApp.kt`
*   `app/src/main/java/cn/loveapple/divination/data/DataImporter.kt`
*   `app/src/main/java/cn/loveapple/divination/data/dao/DivinationRecordDao.kt`
*   `app/src/main/java/cn/loveapple/divination/data/dao/HexagramDao.kt`
*   `app/src/main/java/cn/loveapple/divination/data/database/MeihuaYishuDatabase.kt`
*   `app/src/main/java/cn/loveapple/divination/data/entity/DivinationRecord.kt`
*   `app/src/main/java/cn/loveapple/divination/data/entity/Hexagram.kt`
*   `app/src/main/java/cn/loveapple/divination/data/model/HexagramDetail.kt`
*   `app/src/main/java/cn/loveapple/divination/data/model/YaoCi.kt`
*   `app/src/main/java/cn/loveapple/divination/data/repository/DivinationRecordRepository.kt`
*   `app/src/main/java/cn/loveapple/divination/data/repository/HexagramRepository.kt`
*   `app/src/main/java/cn/loveapple/divination/logic/DivinationEngine.kt`
*   `app/src/main/java/cn/loveapple/divination/ui/screen/DivinationResultScreen.kt`
*   `app/src/main/java/cn/loveapple/divination/ui/screen/DivinationScreen.kt`
*   `app/src/main/java/cn/loveapple/divination/ui/screen/HistoryScreen.kt`
*   `app/src/main/java/cn.loveapple/divination/ui/screen/HomeScreen.kt` (已合并到MainScreen.kt)
*   `app/src/main/java/cn/loveapple/divination/ui/screen/IntroductionScreen.kt`
*   `app/src/main/java/cn/loveapple/divination/ui/screen/MainScreen.kt`
*   `app/src/main/java/cn/loveapple/divination/ui/screen/SettingsScreen.kt`
*   `app/src/main/java/cn/loveapple/divination/ui/theme/Theme.kt`
*   `app/src/main/java/cn/loveapple/divination/ui/theme/Type.kt`
*   `app/src/main/java/cn/loveapple/divination/ui/viewmodel/DivinationViewModel.kt`
*   `app/src/main/java/cn/loveapple/divination/ui/viewmodel/HexagramViewModel.kt`
*   `app/src/main/java/cn/loveapple/divination/ui/viewmodel/ViewModelFactory.kt`
*   `app/src/main/java/cn.loveapple/divination/ui/viewmodel/ViewModelProvider.kt`
*   `app/src/main/java/cn/loveapple/divination/util/ShareUtil.kt`
*   `app/src/main/AndroidManifest.xml`
*   `app/build.gradle.kts`
*   `build.gradle.kts`
*   `settings.gradle.kts`
*   `gradle.properties`
*   `app/proguard-rules.pro`
*   `app/src/main/res/values/colors.xml`
*   `app/src/main/res/values/strings.xml`
*   `app/src/main/res/values/themes.xml`
*   `.gitignore`


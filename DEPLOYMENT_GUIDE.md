# 梅花易数 Android 应用 (Meihua Yishu Android Application) - 部署指南

**项目**: 梅花易数
**版本**: Version 1.0 (MVP)
**目标**: 编译、运行和发布应用。

---

## 1. 环境准备

确保您的开发环境满足以下要求：

*   **操作系统**: Windows, macOS, 或 Linux
*   **IDE**: Android Studio (推荐最新稳定版)
*   **JDK**: Java Development Kit 17 或更高版本
*   **SDK**: Android SDK 34 (Android 14.0)

## 2. 项目结构

项目根目录为 `meihuayishu-android`。

```
meihuayishu-android/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/cn/loveapple/divination/  (Kotlin 源码)
│   │   │   ├── assets/meihuayishu_data.json  (卦象数据)
│   │   │   └── res/values/strings.xml       (字符串资源)
│   ├── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
├── README.md
└── CHANGELOG.md
```

## 3. 运行应用 (Run the Application)

### 3.1. 在 Android Studio 中运行

1.  **打开项目**: 启动 Android Studio，选择 **Open**，然后导航到 `meihuayishu-android` 目录并打开。
2.  **同步 Gradle**: 等待 Android Studio 自动同步项目。如果未自动同步，请点击工具栏中的 **Sync Project with Gradle Files** 按钮。
3.  **选择目标设备**: 在设备选择下拉菜单中，选择一个 Android 模拟器或已连接的物理设备。
4.  **运行**: 点击工具栏中的 **Run 'app'** (绿色播放按钮)。

### 3.2. 命令行运行 (使用 Gradle)

1.  **进入项目根目录**:
    ```bash
    cd meihuayishu-android
    ```
2.  **构建 Debug APK**:
    ```bash
    ./gradlew assembleDebug
    ```
    生成的 APK 文件位于 `app/build/outputs/apk/debug/app-debug.apk`。
3.  **安装到设备**:
    确保您的设备已连接并启用了 USB 调试。
    ```bash
    adb install app/build/outputs/apk/debug/app-debug.apk
    ```

## 4. 发布应用 (Release the Application)

### 4.1. 签名配置

在发布到 Google Play 商店之前，您需要使用发布密钥库 (Release Keystore) 对应用进行签名。

1.  **生成密钥库**: 如果您还没有密钥库，请使用 `keytool` 命令生成。
    ```bash
    keytool -genkey -v -keystore my-release-key.keystore -alias my-key-alias -keyalg RSA -keysize 2048 -validity 10000
    ```
2.  **配置签名**: 在 `app/build.gradle.kts` 中添加签名配置（请替换为您的实际信息）。

    ```kotlin
    // app/build.gradle.kts (示例片段)
    android {
        signingConfigs {
            create("release") {
                storeFile = file("path/to/my-release-key.keystore") // 替换为您的密钥库路径
                storePassword = System.getenv("KEYSTORE_PASSWORD") // 推荐使用环境变量
                keyAlias = "my-key-alias"
                keyPassword = System.getenv("KEY_PASSWORD") // 推荐使用环境变量
            }
        }
        
        buildTypes {
            release {
                // ... 其他配置
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }
    ```

### 4.2. 构建 Release AAB/APK

1.  **构建 Release Bundle (推荐)**:
    ```bash
    ./gradlew bundleRelease
    ```
    生成的 AAB 文件位于 `app/build/outputs/bundle/release/app-release.aab`。这是 Google Play 商店推荐的格式。
2.  **构建 Release APK (可选)**:
    ```bash
    ./gradlew assembleRelease
    ```
    生成的 APK 文件位于 `app/build/outputs/apk/release/app-release.apk`。

## 5. 部署到 Google Play

1.  登录 [Google Play Console](https://play.google.com/console)。
2.  创建一个新的应用。
3.  上传您生成的 `app-release.aab` 文件。
4.  完成商店列表信息、内容分级、定价和分发等步骤。
5.  发布到测试轨道或正式发布。

## 6. 数据更新

卦象数据存储在 `app/src/main/assets/meihuayishu_data.json` 文件中。

*   **更新内容**: 直接编辑此 JSON 文件即可更新卦辞、爻辞的日语解读。
*   **数据结构**: 确保更新后的 JSON 结构与 `cn.loveapple.divination.data.entity.Hexagram` 实体类和 `cn.loveapple.divination.data.DataImporter` 中的解析逻辑保持一致。

---
**番创知库 (FanChuang Knowledge Base) 版权所有 © 2025**


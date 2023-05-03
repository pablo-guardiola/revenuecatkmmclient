RevenueCat HTTP KMM client.

## Getting Started

- Install CocoaPods and CocoaPods Generate in your default Ruby environment:

```
$> gem install cocoapods
$> gem install cocoapods-generate
```

- Run build task to setup both iOS and Android projects:

```
$> ./gradlew build
```

If everything went well, you should be able to run both Android and iOS apps.

### Android

- Import the project: `File > Open > build.gradle.kts`
- Create `revenuecat_api_key.xml` under `androidApp/src/main/res/values` and include your RevenueCat API key:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources xmlns:tools="http://schemas.android.com/tools">
    <string name="revenuecat_api_key" translatable="false" tools:ignore="UnusedResources">YOUR REVENUECAT API KEY</string>
</resources>
```

- Run `androidApp` configuration

### iOS

- *(optionally)* Instead of running `./gradlew build` mentioned above, it's enough to only setup CocoaPods integration with `./gradlew :shared:podspec`
- Create `~/.revenuecat` file and include your RevenueCat API key
- Run `pod install` in `iosApp` directory
- Open the Xcode project `iosApp/iosApp.xcworkspace`
- Run `iosApp` target

#### Debugging Kotlin code in Xcode

[xcode-kotlin](https://github.com/touchlab/xcode-kotlin) plugin adds ability to debug Kotlin code directly from Xcode. This enables a smoother development and integration experience for iOS developers.

- Run `brew install xcode-kotlin`
- Run `xcode-kotlin install`
- Run `xcode-kotlin info` to make sure your Xcode version is listed as supported in the output.
- If you update Xcode, make sure to update plugin as well by running `xcode-kotlin sync`
- Run `pod install` in `iosApp` directory
- Open the Xcode project `iosApp/iosApp.xcworkspace`

You should now be able to place breakpoints in Kotlin files from folders `commonMain` and `iosMain`. Unlike Android Studio Xcode always builds apps in Debug mode, so breakpoints will always work.

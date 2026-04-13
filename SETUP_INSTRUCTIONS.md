# 🚀 Setup Instructions - GlowUp Hair Studio

Follow these steps to get the app running on your machine.

---

## 📋 Prerequisites

Before starting, make sure you have:

1. ✅ **Android Studio** Hedgehog (2023.1.1) or newer
   - Download: https://developer.android.com/studio
   
2. ✅ **JDK 17**
   - Included with Android Studio
   - Or download: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

3. ✅ **Android SDK 34**
   - Install via Android Studio SDK Manager

4. ✅ **Git** (optional, for version control)
   - Download: https://git-scm.com/

---

## 🛠️ Step-by-Step Setup

### Step 1: Extract the Project

```bash
# Extract the ZIP file
unzip GlowUpHairStudio.zip

# Navigate to project directory
cd GlowUpHairStudio
```

---

### Step 2: Open in Android Studio

1. Launch **Android Studio**
2. Click **File → Open**
3. Navigate to `GlowUpHairStudio` folder
4. Click **OK**
5. Wait for **Gradle Sync** to complete (may take 2-5 minutes first time)

---

### Step 3: Configure SDK (if needed)

If you see SDK errors:

1. Go to **File → Project Structure**
2. Under **SDK Location**, set:
   - **Android SDK Location:** `/path/to/your/Android/sdk`
   - **JDK Location:** JDK 17 path
3. Click **Apply** → **OK**

---

### Step 4: Sync Gradle

1. Click **File → Sync Project with Gradle Files**
2. Or click the 🐘 **Gradle sync** icon in toolbar
3. Wait for sync to complete

---

### Step 5: Build the Project

**Option A: Via Android Studio**
- Click **Build → Make Project**
- Or press `Ctrl+F9` (Windows/Linux) or `Cmd+F9` (Mac)

**Option B: Via Terminal**
```bash
./gradlew build
```

---

### Step 6: Run the App

**Option A: On Emulator**

1. Click **Tools → Device Manager**
2. Create a new virtual device:
   - Device: Pixel 6
   - System Image: Android 14 (API 34)
   - Click **Finish**
3. Click ▶️ **Run** button
4. Select your emulator

**Option B: On Physical Device**

1. Enable **Developer Options** on your Android phone:
   - Go to **Settings → About Phone**
   - Tap **Build Number** 7 times
   
2. Enable **USB Debugging**:
   - **Settings → Developer Options → USB Debugging**

3. Connect phone via USB

4. Click ▶️ **Run** button

5. Select your device

---

## 🔑 Test the App

### Demo Login Credentials

**Customer Account:**
```
Email: customer@demo.com
Password: demo123
```

**Admin Account:**
```
Email: admin@demo.com
Password: admin123
```

---

## ✅ What to Test

1. **Login Flow**
   - Launch app
   - Use demo credentials
   - Should navigate to Home screen

2. **Home Screen**
   - See loyalty progress (7/10 visits)
   - See upcoming appointments
   - Click "Browse Services"

3. **Services Screen**
   - See 6 pre-loaded services
   - Prices and durations visible
   - Click "Book Now" (booking flow not yet implemented)

4. **Navigation**
   - Test back button
   - Navigation should work smoothly

---

## 🐛 Troubleshooting

### Issue: Gradle Sync Failed

**Solution:**
```bash
# Clean and rebuild
./gradlew clean
./gradlew build
```

---

### Issue: SDK Not Found

**Solution:**
1. Open `local.properties`
2. Add/update:
```properties
sdk.dir=/path/to/your/Android/sdk
```

---

### Issue: Build Error - "Namespace not specified"

**Solution:**
- Make sure `namespace` is set in `app/build.gradle.kts`:
```kotlin
android {
    namespace = "com.glowup.hairstudio"
    compileSdk = 34
    // ...
}
```

---

### Issue: Dependencies Download Slow

**Solution:**
```bash
# Use Gradle offline mode after first successful sync
./gradlew build --offline
```

---

### Issue: Emulator Won't Start

**Solution:**
1. Enable **Virtualization** in BIOS/UEFI
2. Restart Android Studio
3. Try creating new emulator with lower specs

---

## 📱 Recommended Emulator Settings

- **Device:** Pixel 6 or Pixel 7
- **System Image:** Android 14 (API 34)
- **RAM:** 2048 MB
- **Internal Storage:** 2048 MB
- **Graphics:** Hardware - GLES 2.0

---

## 🔧 Advanced Configuration

### Change Package Name

Edit `app/build.gradle.kts`:
```kotlin
defaultConfig {
    applicationId = "com.yourcompany.yourapp"
    // ...
}
```

Then refactor package in Android Studio:
- Right-click `com.glowup.hairstudio` package
- **Refactor → Rename**

---

### Add API Keys (Future Firebase Setup)

Edit `local.properties`:
```properties
FIREBASE_API_KEY=your_firebase_key
GOOGLE_MAPS_API_KEY=your_maps_key
```

**Note:** Currently app runs in mock mode - no keys needed.

---

## 📦 Build APK for Testing

### Debug APK
```bash
./gradlew assembleDebug
```
Output: `app/build/outputs/apk/debug/app-debug.apk`

### Install on Device
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

---

## ✅ Success Checklist

- [ ] Android Studio installed
- [ ] Project opened successfully
- [ ] Gradle sync completed without errors
- [ ] App builds successfully
- [ ] App runs on emulator/device
- [ ] Login works with demo credentials
- [ ] Home screen displays correctly
- [ ] Services screen loads

---

## 📞 Need Help?

If you encounter issues:

1. Check **Build** tab in Android Studio for errors
2. Check **Logcat** for runtime errors
3. Clean and rebuild project
4. Invalidate caches: **File → Invalidate Caches → Restart**

---

## 🎯 Next Steps

After successful setup:

1. Explore the codebase
2. Review `README.md` for architecture details
3. Add remaining screens (Signup, Booking, etc.)
4. Customize colors/theme in `presentation/theme/`
5. Integrate real backend (Firebase/REST API)

---

**Happy Coding! 💻✨**

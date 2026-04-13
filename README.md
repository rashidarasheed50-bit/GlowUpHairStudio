# 💇 GlowUp Hair Studio - Android App

A modern, production-ready salon booking app built with **Jetpack Compose**, **MVVM + Clean Architecture**, and **Room Database**.

## 📱 About The Project

**GlowUp Hair Studio** is a complete mobile solution for hair salons to manage appointments, services, and customer loyalty programs. Built for **Austin, Texas** based salon with 3 stylists.

### ✨ Key Features

- 📅 **Online Booking** - Book appointments 24/7 with stylist selection
- 💇 **Service Menu** - Haircut, Color, Highlights, Blowout with pricing
- 🔔 **Push Notifications** - 1 hour before appointment reminders
- ⭐ **Reviews System** - In-app feedback and ratings
- 🎁 **Loyalty Program** - Every 10th visit free
- 👨‍💼 **Admin Panel** - Manage bookings, view analytics
- 🎨 **Beautiful UI** - Pink + White + Gold premium design

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────┐
│         PRESENTATION LAYER              │
│  (UI - Jetpack Compose + ViewModels)    │
└─────────────────────────────────────────┘
                  ↕️
┌─────────────────────────────────────────┐
│           DOMAIN LAYER                  │
│     (Use Cases + Domain Models)         │
└─────────────────────────────────────────┘
                  ↕️
┌─────────────────────────────────────────┐
│            DATA LAYER                   │
│  (Repositories + Room DB + Mock Data)   │
└─────────────────────────────────────────┘
```

**Pattern:** MVVM + Clean Architecture + Repository Pattern

---

## 🛠️ Tech Stack

| Category | Technology |
|----------|------------|
| **Language** | Kotlin 1.9.20 |
| **UI Framework** | Jetpack Compose (Material3) |
| **Architecture** | MVVM + Clean Architecture |
| **Dependency Injection** | Hilt 2.48.1 |
| **Database** | Room 2.6.1 |
| **Navigation** | Navigation Compose 2.7.6 |
| **Async** | Coroutines + Flow |
| **Storage** | DataStore Preferences |
| **Min SDK** | 24 (Android 7.0) |
| **Target SDK** | 34 (Android 14) |

---

## 📂 Project Structure

```
app/src/main/java/com/glowup/hairstudio/
│
├── di/                          # Dependency Injection
│   ├── AppModule.kt
│   ├── DatabaseModule.kt
│   └── RepositoryModule.kt
│
├── data/                        # Data Layer
│   ├── local/
│   │   ├── entity/             # Room entities
│   │   ├── dao/                # Data Access Objects
│   │   └── database/           # Room database
│   ├── repository/             # Repository implementations
│   └── mock/                   # Mock data provider
│
├── domain/                      # Domain Layer
│   ├── model/                  # Domain models
│   ├── repository/             # Repository interfaces
│   └── usecase/                # Business logic
│       ├── auth/
│       ├── appointment/
│       ├── service/
│       ├── stylist/
│       ├── review/
│       └── loyalty/
│
├── presentation/                # Presentation Layer
│   ├── theme/                  # App theme (Color, Type, Spacing)
│   ├── components/             # Reusable UI components
│   ├── navigation/             # Navigation graph
│   └── screens/                # UI screens + ViewModels
│       ├── splash/
│       ├── login/
│       ├── home/
│       └── services/
│
├── utils/                       # Utilities
│   ├── Constants.kt
│   ├── DateTimeUtils.kt
│   ├── ValidationUtils.kt
│   └── PreferenceManager.kt
│
└── GlowUpApplication.kt        # Application class
```

---

## 🚀 Getting Started

### Prerequisites

- **Android Studio** Hedgehog (2023.1.1) or newer
- **JDK** 17
- **Android SDK** 34
- **Gradle** 8.2+

### Installation Steps

1. **Clone/Extract the project**
   ```bash
   cd GlowUpHairStudio
   ```

2. **Open in Android Studio**
   - File → Open → Select `GlowUpHairStudio` folder
   - Wait for Gradle sync to complete

3. **Build the project**
   ```bash
   ./gradlew build
   ```

4. **Run on emulator or device**
   - Click ▶️ Run button in Android Studio
   - Or: `./gradlew installDebug`

---

## 🔑 Demo Accounts

The app comes with pre-loaded demo accounts:

### Customer Account
```
Email: customer@demo.com
Password: demo123
```

### Admin Account
```
Email: admin@demo.com
Password: admin123
```

---

## 📊 Database Schema

### Tables

1. **users** - User accounts (customer/admin)
2. **services** - Available salon services
3. **stylists** - Salon stylists (3 pre-loaded)
4. **appointments** - Booking records
5. **reviews** - Customer reviews

### Mock Data

Pre-populated on first launch:
- ✅ 2 demo users (1 customer, 1 admin)
- ✅ 6 services (Haircut, Color, Highlights, etc.)
- ✅ 3 stylists (Emma, Lisa, Anna)
- ✅ Sample appointments for customer
- ✅ Sample reviews

---

## 🎨 Design System

### Color Palette

| Color | Hex | Usage |
|-------|-----|-------|
| Primary Pink | `#FF69B4` | Buttons, icons, accents |
| Light Pink | `#FFC0CB` | Backgrounds, cards |
| Gold | `#FFD700` | Premium accents |
| White | `#FFFFFF` | Surfaces, text on dark |
| Text Primary | `#2D2D2D` | Main text |

### Typography

- **Font Family:** System Default (Poppins-style weights)
- **Display:** Bold 32sp
- **Headline:** SemiBold 20sp
- **Body:** Normal 16sp
- **Label:** Medium 14sp

---

## 📱 Current Features (MVP)

### ✅ Implemented
- [x] Splash screen with auth check
- [x] Login with demo accounts
- [x] Home screen with loyalty tracker
- [x] Services browsing
- [x] Mock database with sample data
- [x] Clean Architecture with MVVM
- [x] Hilt dependency injection
- [x] Navigation setup
- [x] Material3 theme

### 🚧 To Be Implemented
- [ ] Sign Up flow
- [ ] Booking flow (date/time picker, stylist selection)
- [ ] Appointments management (view, cancel, reschedule)
- [ ] Reviews submission
- [ ] Profile management
- [ ] Admin dashboard
- [ ] Push notifications (WorkManager)
- [ ] Onboarding screens

---

## 🔧 Configuration

### API Keys (Future Firebase Integration)

Edit `local.properties`:
```properties
FIREBASE_API_KEY=your_key_here
GOOGLE_MAPS_API_KEY=your_key_here
```

**Note:** Currently runs in mock/demo mode. No API keys needed.

---

## 🧪 Testing

### Run Unit Tests
```bash
./gradlew test
```

### Run Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

---

## 📦 Build APK

### Debug APK
```bash
./gradlew assembleDebug
```
Output: `app/build/outputs/apk/debug/app-debug.apk`

### Release APK (Signed)
```bash
./gradlew assembleRelease
```

---

## 🤝 Contributing

This is a client project template. To extend:

1. **Add new screens** - Follow pattern in `presentation/screens/`
2. **Add new use cases** - Follow pattern in `domain/usecase/`
3. **Update database** - Modify entities in `data/local/entity/`
4. **Add features** - Follow Clean Architecture layers

---

## 📝 Code Guidelines

- **Kotlin** style guide (official)
- **MVVM** pattern for UI
- **Clean Architecture** for separation of concerns
- **Hilt** for dependency injection
- **StateFlow** for state management
- **Coroutines** for async operations

---

## 🐛 Known Issues

- Firebase integration pending (currently mock mode)
- Notification system needs WorkManager implementation
- Admin screens need completion
- Need real-time booking conflict detection

---

## 📄 License

Proprietary - Built for GlowUp Hair Studio, Austin, Texas

---

## 👨‍💻 Developer

**Built by:** Claude (Anthropic AI)  
**For:** Ali - Forex Trader & Android Developer  
**Date:** April 2026  
**Version:** 1.0.0

---

## 📞 Support

For issues or questions:
- Create an issue in the repository
- Contact development team

---

## 🎯 Roadmap

### Phase 1 (Current - MVP)
- ✅ Authentication
- ✅ Services browsing
- ✅ Basic UI/UX

### Phase 2 (Next)
- [ ] Complete booking flow
- [ ] Appointment management
- [ ] Reviews system

### Phase 3 (Future)
- [ ] Push notifications
- [ ] Firebase backend integration
- [ ] Analytics dashboard
- [ ] Payment integration

---

**Made with ❤️ and Jetpack Compose**

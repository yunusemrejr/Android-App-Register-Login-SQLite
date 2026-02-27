# Restaurant Menu App

A modern Android application for restaurant menu management with user authentication.

## Features

✨ **Modern UI/UX**
- Material Design 3 components
- Clean and professional interface
- Responsive layouts with proper spacing
- Beautiful color scheme

🔐 **Secure Authentication**
- User registration and login
- Password hashing with SHA-256
- Salt-based security
- Input validation

📱 **Menu Display**
- RecyclerView-based menu listing
- Categorized menu items
- Clean card-based design
- Easy to navigate

## Technical Stack

- **Language**: Java
- **Min SDK**: 21 (Android 5.0)
- **Target SDK**: 34 (Android 14)
- **Architecture**: Modern Android with ViewBinding
- **Database**: SQLite with secure password storage
- **UI Framework**: Material Design 3

## Improvements Made

### 1. **Updated Dependencies**
- Android Gradle Plugin: 7.1.2 → 8.2.2
- Compile SDK: 32 → 34
- Target SDK: 32 → 34
- Material Components: 1.5.0 → 1.11.0
- AppCompat: 1.4.1 → 1.6.1
- Added RecyclerView and CardView support

### 2. **Security Enhancements**
- Implemented password hashing with SHA-256
- Added salt generation for each user
- Proper input validation
- SQL injection protection
- Secure database operations with proper resource management

### 3. **Code Quality**
- Migrated to ViewBinding (no more findViewById)
- Proper package structure (models, adapters, utils, database, activities)
- Added utility classes for validation and password management
- Proper error handling and resource cleanup
- Cursor management to prevent memory leaks

### 4. **UI/UX Modernization**
- Material Design 3 theming
- Professional color scheme (Orange primary, Blue secondary, Yellow accent)
- Modern layouts with CardViews
- TextInputLayout with proper hints and icons
- RecyclerView for efficient menu display
- Proper spacing and typography
- Responsive design

### 5. **Features Added**
- Logout functionality
- Better error messages with field-specific validation
- Password visibility toggle
- Skip login option
- Modern toolbar with logout button

## Project Structure

```
app/src/main/java/com/example/restorant/
├── activities/
│   ├── MainActivity.java          # Registration screen
│   ├── GirisYapActivity.java      # Login screen
│   └── MenuActivity.java          # Menu display
├── adapters/
│   └── MenuAdapter.java           # RecyclerView adapter
├── database/
│   └── VeriTabani.java            # Database helper
├── models/
│   └── MenuItem.java              # Menu item model
└── utils/
    ├── PasswordUtils.java         # Password hashing utilities
    └── ValidationUtils.java       # Input validation utilities
```

## Building the App

1. Open the project in Android Studio
2. Sync Gradle files
3. Build and run on an emulator or device

## Version History

- **v2.0** - Complete modernization with Material Design 3, security improvements, and better architecture
- **v1.0** - Initial version

## License

See LICENSE file for details.

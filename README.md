# MultiCord - Multiple Discord Accounts App
A personal Android app to run 5 Discord accounts simultaneously with bottom tab switching.

---

## How to Build & Install (Free, No PC Required)

### Option A: Build Online Using GitHub + Buildozer (Recommended)

1. Go to **https://github.com** and create a free account
2. Create a new repository called `MultiCord`
3. Upload all these project files to the repo
4. Go to **https://appetize.io** or use **GitHub Actions** to build the APK

### Option B: Build Using Android Studio (Needs PC)
1. Install Android Studio from https://developer.android.com/studio
2. Open this project folder
3. Click **Build → Build Bundle(s)/APK(s) → Build APK(s)**
4. APK will be in `app/build/outputs/apk/debug/`
5. Transfer APK to your Samsung F12 and install

### Option C: Use Online Android Builder - MIT App Inventor
Since you're on mobile only, this is the easiest:
1. Go to **https://appinventor.mit.edu**
2. Sign in with Google
3. Create a new project
4. Use the WebViewer component to replicate this app
5. Export as APK directly

---

## App Features
- ✅ 5 Discord accounts (each fully isolated)
- ✅ Bottom tab bar to switch accounts
- ✅ Each account has separate login/cookies
- ✅ Back button support per account
- ✅ Discord dark theme UI
- ✅ Full screen, no ads, no tracking

## Account Tabs
| Tab | Emoji | Label |
|-----|-------|-------|
| 1   | 👤    | Acc 1 |
| 2   | 👥    | Acc 2 |
| 3   | 🧑    | Acc 3 |
| 4   | 🙋    | Acc 4 |
| 5   | 😎    | Acc 5 |

## How It Works
Each tab opens discord.com/app in a separate WebView with isolated cookies and storage, so each one can be logged into a different Discord account simultaneously.

---

## To Add More Accounts
In `MainActivity.java`, change:
```java
private static final int ACCOUNT_COUNT = 5;
```
To any number you want (e.g. 8), and add more labels/emojis to the arrays.

---

## Files in This Project
```
DiscordMulti/
├── app/
│   ├── build.gradle
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/discordmulti/
│       │   └── MainActivity.java
│       └── res/
│           ├── layout/activity_main.xml
│           ├── values/styles.xml
│           └── xml/network_security_config.xml
├── build.gradle
└── settings.gradle
```

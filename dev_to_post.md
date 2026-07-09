Let’s be honest: **Dynamic app icons are amazing for user experience, but they are a total nightmare to implement in React Native.**

If you’ve ever tried to let your users switch between a "Dark Mode" icon or a "Premium Theme" icon in an Expo app, you already know the pain. You find yourself knee-deep in native Android documentation, manually hacking your `AndroidManifest.xml` with `activity-alias` tags, fighting with Gradle build errors, and manually dragging dozens of density-specific .png files into your native build folders.

It’s tedious. It’s error-prone. And if you run `npx expo prebuild --clean`, **it wipes all your hard work away.**

I finally had enough. I wanted a solution where I could just drop a PNG into my project, write one line of JavaScript, and be done with it. 

Since it didn't exist, I built it. 

![App Demo](https://raw.githubusercontent.com/alanjoseph77/expo-dynamic-icons/main/video.gif)

---

## Introducing: expo-dynamic-icons

**[expo-dynamic-icons](https://www.npmjs.com/package/expo-dynamic-icons)** is a native module and Expo Config Plugin that completely automates changing your Android app icon at runtime. 

* **Zero Native Config:** No writing Kotlin. No touching XML files.
* **Fully Automated Assets:** It hooks into the Expo `prebuild` phase and automatically generates the aliases and moves your local .png files into the compiled native folders.
* **Instant Switching:** Call one JavaScript function, and the Android OS instantly toggles the home screen icon.

---

## The 3-Step Setup

It is unbelievably easy to use. 

### 1. Install it
```bash
npm install expo-dynamic-icons
```

### 2. Tell the plugin where your images are (app.json)
Just point the plugin to the local images you want to use as your alternative icons:

```json
{
  "expo": {
    "plugins": [
      [
        "expo-dynamic-icons",
        {
          "icons": {
            "orange": { "image": "./assets/orange.png" },
            "purple": { "image": "./assets/purple.png" }
          }
        }
      ]
    ]
  }
}
```

### 3. Change the icon in JavaScript!
That’s it. Now you can trigger the icon change from anywhere in your app:

```tsx
import { TouchableOpacity, Text } from 'react-native';
import { setAppIcon } from 'expo-dynamic-icons';

export default function SettingsScreen() {
  return (
    <TouchableOpacity onPress={() => setAppIcon('orange')}>
      <Text>Switch to Orange Theme</Text>
    </TouchableOpacity>
  );
}
```
*(Remember: Because this modifies native code, you must build the app with `npx expo run:android` rather than Expo Go!)*

---

## Open Source & Next Steps

I built this to solve a massive pain point for myself, and I hope it saves some of you a few hours of digging through native Android documentation! 

*(Currently, this library supports Android, but I am actively working on writing the iOS Swift bridging!)*

If this library saves you a headache, I’d absolutely love it if you gave it a star on GitHub!

* **NPM:** [https://www.npmjs.com/package/expo-dynamic-icons](https://www.npmjs.com/package/expo-dynamic-icons)
* **GitHub:** [https://github.com/alanjoseph77/expo-dynamic-icons](https://github.com/alanjoseph77/expo-dynamic-icons)

Let me know in the comments if you have any feature requests, or if you've ever experienced the pain of manual AndroidManifest editing! Happy coding!

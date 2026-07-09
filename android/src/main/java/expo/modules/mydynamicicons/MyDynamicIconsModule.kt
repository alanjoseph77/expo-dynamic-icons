package expo.modules.mydynamicicons

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class MyDynamicIconsModule : Module() {
  override fun definition() = ModuleDefinition {
    Name("MyDynamicIcons")

    Function("setAppIcon") { iconName: String ->
      val context = appContext.reactContext ?: throw Exception("React context is null")
      val packageManager = context.packageManager
      val packageName = context.packageName

      val prefs = context.getSharedPreferences("MyDynamicIconsPrefs", Context.MODE_PRIVATE)
      val currentIcon = prefs.getString("currentIcon", "default") ?: "default"

      if (currentIcon == iconName) {
        return@Function true // Already set
      }

      val mainActivity = "$packageName.MainActivity"
      
      // Determine the component names
      val oldComponentClass = if (currentIcon == "default") mainActivity else "$mainActivity$currentIcon"
      val newComponentClass = if (iconName == "default") mainActivity else "$mainActivity$iconName"

      val oldComponent = ComponentName(packageName, oldComponentClass)
      val newComponent = ComponentName(packageName, newComponentClass)

      try {
        // Enable new icon
        packageManager.setComponentEnabledSetting(
          newComponent,
          PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
          PackageManager.DONT_KILL_APP
        )

        // Disable old icon
        packageManager.setComponentEnabledSetting(
          oldComponent,
          PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
          PackageManager.DONT_KILL_APP
        )

        // Save new state
        prefs.edit().putString("currentIcon", iconName).apply()
        
        return@Function true
      } catch (e: Exception) {
        println("Failed to set app icon: ${e.message}")
        throw Exception("Failed to set app icon. Did you add the activity-alias to AndroidManifest.xml?")
      }
    }

    Function("getAppIcon") {
      val context = appContext.reactContext ?: return@Function "default"
      val prefs = context.getSharedPreferences("MyDynamicIconsPrefs", Context.MODE_PRIVATE)
      return@Function prefs.getString("currentIcon", "default") ?: "default"
    }
  }
}

import { NativeModule, requireNativeModule } from 'expo-modules-core';

declare class MyDynamicIconsModule extends NativeModule<{}> {}

export default requireNativeModule<MyDynamicIconsModule>('MyDynamicIcons');

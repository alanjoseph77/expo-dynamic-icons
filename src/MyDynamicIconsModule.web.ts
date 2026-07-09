import { registerWebModule, NativeModule } from 'expo';

class MyDynamicIconsModule extends NativeModule<{}> {}

export default registerWebModule(MyDynamicIconsModule, 'MyDynamicIconsModule');

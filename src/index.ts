import MyDynamicIconsModule from './MyDynamicIconsModule';

export function setAppIcon(name: string): void {
  return MyDynamicIconsModule.setAppIcon(name);
}

export function getAppIcon(): string {
  return MyDynamicIconsModule.getAppIcon();
}

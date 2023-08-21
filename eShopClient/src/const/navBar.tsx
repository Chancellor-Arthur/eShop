import { RoutePath } from '@/const/routes';
import { HelpOutline20Icon, Home20Icon, NotificationActive20Icon, Settings20Icon, Today20Icon } from '@/shared/icons';
import { ReactNode } from 'react';

export interface NavBarItem {
  href: string;
  label: string;
  icon?: ReactNode;
}

export const navBarLinks: NavBarItem[] = [
  { href: RoutePath.Main, label: 'Главная', icon: <Home20Icon /> },
  { href: RoutePath.Notifications, label: 'Уведомления', icon: <NotificationActive20Icon /> },
  { href: RoutePath.Events, label: 'События', icon: <Today20Icon /> },
];

export const navBarSubLinks: NavBarItem[] = [
  { href: RoutePath.Settings, label: 'Настройки', icon: <Settings20Icon /> },
  { href: RoutePath.Auth, label: 'Войти', icon: <HelpOutline20Icon /> },
];

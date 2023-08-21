import { RoutePath } from '@/const/routes';
import { lazy } from 'react';
import type { RouteObject } from 'react-router-dom';
import DefaultLayout from '@/layouts/DefaultLayout';
const MainPage = lazy(() => import('@/pages/MainPage'));
const EventsPage = lazy(() => import('@/pages/EventsPage'));
const NotificationsPage = lazy(() => import('@/pages/NotificationsPage'));
const AuthPage = lazy(() => import('@/pages/AuthPage'));
const SettingsPage = lazy(() => import('@/pages/SettingsPage'));

export const ROUTES: RouteObject[] = [
  {
    path: RoutePath.Main,
    element: <DefaultLayout />,
    children: [
      { index: true, element: <MainPage />, path: RoutePath.Main },
      { element: <EventsPage />, path: RoutePath.Events },
      { element: <NotificationsPage />, path: RoutePath.Notifications },
      { element: <AuthPage />, path: RoutePath.Auth },
      { element: <SettingsPage />, path: RoutePath.Settings },
    ],
  },
  { path: '*', element: 'Not found' },
];

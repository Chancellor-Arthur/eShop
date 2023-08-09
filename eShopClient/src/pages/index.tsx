import { RoutePath } from '@/processes/route';
import { lazy } from 'react';
import type { RouteObject } from 'react-router-dom';
import Layout from '@/widgets/Layout';
const MainPage = lazy(() => import('@/pages/MainPage'));
const EventsPage = lazy(() => import('@/pages/EventsPage'));
const NotificationsPage = lazy(() => import('@/pages/NotificationsPage'));
const HelpPage = lazy(() => import('@/pages/HelpPage'));
const SettingsPage = lazy(() => import('@/pages/SettingsPage'));

export const ROUTES: RouteObject[] = [
  {
    path: RoutePath.Main,
    element: <Layout />,
    children: [
      { index: true, element: <MainPage />, path: RoutePath.Main },
      { element: <EventsPage />, path: RoutePath.Events },
      { element: <NotificationsPage />, path: RoutePath.Notifications },
      { element: <HelpPage />, path: RoutePath.Help },
      { element: <SettingsPage />, path: RoutePath.Settings },
    ],
  },
  { path: '*', element: 'Not found' },
];

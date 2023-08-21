import { FC, Suspense } from 'react';
import Navbar from '@/components/layout/Navbar';
import { Outlet } from 'react-router-dom';
import { AppShell } from '@mantine/core';

const DefaultLayout: FC = () => {
  return (
    <AppShell
      padding="md"
      navbar={<Navbar />}
      styles={(theme) => ({
        main: {
          backgroundColor: theme.colors.gray[0],
        },
      })}
    >
      <Suspense fallback="Loading...">
        <Outlet />
      </Suspense>
    </AppShell>
  );
};

export default DefaultLayout;

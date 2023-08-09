import { FC, Suspense } from "react";
import { Navbar } from "@/entities/layout";
import { Outlet } from "react-router-dom";
import { AppShell } from "@mantine/core";

const Layout: FC = () => {
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

export default Layout;

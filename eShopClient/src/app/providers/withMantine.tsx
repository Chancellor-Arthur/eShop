import { FC } from 'react';
import { MantineProvider } from '@mantine/core';

export const withMantine = (Component: FC) => () =>
  (
    <MantineProvider
      withNormalizeCSS
      withGlobalStyles
      theme={{
        fontFamily: 'Lato',
        primaryColor: 'violet',
        black: '#2C2E33',
        fontSizes: {
          xs: '12px',
          sm: '13px',
          md: '14px',
          lg: '16px',
          xl: '20px',
        },
      }}
    >
      {<Component />}
    </MantineProvider>
  );

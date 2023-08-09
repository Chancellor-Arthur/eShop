import { FC } from 'react';
import { useRoutes } from 'react-router-dom';
import { ROUTES } from '@/pages';
import { withProviders } from './providers';
import { Fonts } from './styles';

const App: FC = () => {
  return (
    <>
      <Fonts />
      {useRoutes(ROUTES)}
    </>
  );
};

export default withProviders(App);

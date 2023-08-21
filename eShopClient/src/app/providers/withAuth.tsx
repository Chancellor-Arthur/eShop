import { FC, useContext } from 'react';
import { StoreContext } from '@/store/globalStore';
import { useLocation, useNavigate } from 'react-router-dom';
import { RoutePath } from '@/const/routes';

export const withAuth = (Component: FC) => () => {
  // const location = useLocation();
  // const navigate = useNavigate();
  // const userStore = useContext(StoreContext);
  //
  // if (
  //   !userStore.token &&
  //   (location.pathname.startsWith(RoutePath.Main) || !location.pathname.startsWith(RoutePath.Auth))
  // ) {
  //   navigate(RoutePath.Auth);
  //   return null;
  // }

  return <Component />;
};

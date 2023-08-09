import { FC } from 'react';
import { BrowserRouter } from 'react-router-dom';

export const withRouter = (Component: FC) => () => <BrowserRouter>{<Component />}</BrowserRouter>;

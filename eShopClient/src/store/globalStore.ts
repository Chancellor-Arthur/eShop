import { createContext } from 'react';

import { UserStore } from '@/store/UserStore';

export const globalStore = new UserStore();
export const StoreContext = createContext<UserStore>(globalStore);

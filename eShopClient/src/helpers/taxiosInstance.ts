import { EShopApi } from '@/app/types/EShopApi';
import { Taxios } from '@simplesmiler/taxios';
import Axios from 'axios';
import { globalStore } from '@/store/globalStore';
import Cookies from 'js-cookie';

export const axios = Axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
});

export const taxios = new Taxios<EShopApi>(axios);

axios.interceptors.request.use((config) => {
  if (config.headers && globalStore.token) config.headers.Authorization = `Bearer ${globalStore.token}`;
  return config;
});

axios.interceptors.response.use(
  (res) => res,
  async (err) => {
    const originalConfig = err.config;

    if (!Axios.isAxiosError(err)) throw err;

    if (err.response?.status === 401) {
      const username = Cookies.get('username');
      if (!username) throw err;

      const password = Cookies.get('password');
      if (!password) throw err;

      const newToken = await globalStore.signIn({ username, password });
      if (!newToken) throw err;

      originalConfig.headers.Authorization = `Bearer ${globalStore.token}`;
      return axios(originalConfig);
    }
    throw err;
  },
);

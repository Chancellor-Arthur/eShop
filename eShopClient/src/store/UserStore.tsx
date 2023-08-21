import jwt_decode from 'jwt-decode';
import { makeAutoObservable, runInAction } from 'mobx';
import { EShopApi } from '@/app/types/EShopApi';
import { signIn } from '@/helpers/fetchers/auth';
import Cookies from 'js-cookie';

export type TokenPayload = { sub?: string; roles: string[] };

export class UserStore {
  username: string | null = null;
  roles: string[] = [];
  token: string | null = null;

  constructor() {
    makeAutoObservable(this);
  }

  async signIn(userCredentials: EShopApi.AuthInputDto) {
    const { token } = await signIn(userCredentials);
    if (!token) return;

    const tokenPayload = jwt_decode(token) as TokenPayload | undefined;
    if (!tokenPayload) return;

    Cookies.set('username', userCredentials.username, { domain: 'localhost' });
    Cookies.set('password', userCredentials.password, { domain: 'localhost' });

    runInAction(() => {
      this.username = tokenPayload.sub ?? null;
      this.roles.push(...tokenPayload.roles);
      this.token = token;
    });

    return token;
  }

  async logOut() {
    runInAction(() => {
      this.username = null;
      this.roles = [];
      this.token = null;
    });
  }
}

import { taxios } from '@/helpers/taxiosInstance';

export const signIn = (inputValues: { username: string; password: string }) => taxios.$post('/auth/login', inputValues);

export const signUp = (inputValues: { username: string; password: string; confirmPassword: string; email: string }) =>
  taxios.$post('/auth/registration', inputValues);

import { taxios } from '@/helpers/taxiosInstance';

export const getAll = (inputValues?: { brandId?: number; typeId?: number }) =>
  taxios.$get('/devices', { query: inputValues });

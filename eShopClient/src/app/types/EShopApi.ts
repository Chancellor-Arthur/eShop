export namespace EShopApi {
  export interface TypeInputDto {
    name: string;
  }
  export interface DefaultExceptionPayload {
    error?: string;
    message?: string;
    path?: string;
    status?: number;
    timestamp?: string;
  }
  export interface TypeOutputDto {
    id?: number;
    name?: string;
  }
  export interface BadRequestExceptionPayload {
    error?: string;
    errors?: EShopApi.Error[];
    message?: string;
    path?: string;
    status?: number;
    timestamp?: string;
  }
  export interface Error {
    arguments?: {}[];
    bindingFailure?: boolean;
    code?: string;
    codes?: string[];
    defaultMessage?: string;
    field?: string;
    objectName?: string;
    rejectedValue?: string;
  }
  export interface FileOutputDto {
    id?: number;
    name?: string;
  }
  export interface DeviceInfoInputDto {
    description: string;
    title: string;
  }
  export interface DeviceInputDto {
    brandId?: number;
    deviceInfos?: EShopApi.DeviceInfoInputDto[];
    fileId?: number;
    name: string;
    price?: number;
    typeId?: number;
  }
  export interface BrandOutputDto {
    id?: number;
    name?: string;
  }
  export interface DeviceOutputDto {
    brand?: EShopApi.BrandOutputDto;
    devicesInfo?: EShopApi.DeviceInfoInputDto[];
    file?: EShopApi.FileOutputDto;
    id?: number;
    name?: string;
    price?: number;
    rating?: number;
    type?: EShopApi.TypeOutputDto;
  }
  export interface BrandInputDto {
    name: string;
  }
  export interface UserInputDto {
    confirmPassword: string;
    email: string;
    password: string;
    username: string;
  }
  export interface AuthOutputDto {
    token?: string;
  }
  export interface AuthInputDto {
    password: string;
    username: string;
  }
  export interface UserOutputDto {
    email?: string;
    roles?: string[];
    username?: string;
  }
  export interface DevicesPage {
    content?: EShopApi.DeviceOutputDto[];
    empty?: boolean;
    first?: boolean;
    last?: boolean;
    number?: number;
    numberOfElements?: number;
    pageable?: EShopApi.PageableObject;
    size?: number;
    sort?: EShopApi.SortObject;
    totalElements?: number;
    totalPages?: number;
  }
  export interface PageableObject {
    offset?: number;
    pageNumber?: number;
    pageSize?: number;
    paged?: boolean;
    sort?: EShopApi.SortObject;
    unpaged?: boolean;
  }
  export interface SortObject {
    empty?: boolean;
    sorted?: boolean;
    unsorted?: boolean;
  }
}

export interface EShopApi {
  version: '1';
  routes: {
    '/types': {
      GET: {
        response: EShopApi.TypeOutputDto[];
      };
      POST: {
        body: EShopApi.TypeInputDto;
      };
    };
    '/files': {
      POST: {
        body?: {
          file: string;
        };
      };
    };
    '/devices': {
      GET: {
        query?: {
          brandId?: number;
          typeId?: number;
          page?: number;
          size?: number;
          sort?: string[];
        };
        response: EShopApi.DevicesPage;
      };
      POST: {
        body: EShopApi.DeviceInputDto;
      };
    };
    '/brands': {
      GET: {
        response: EShopApi.BrandOutputDto[];
      };
      POST: {
        body: EShopApi.BrandInputDto;
      };
    };
    '/auth/registration': {
      POST: {
        body: EShopApi.UserInputDto;
      };
    };
    '/auth/login': {
      POST: {
        body: EShopApi.AuthInputDto;
        response: EShopApi.AuthOutputDto;
      };
    };
    '/users': {
      GET: {
        response: EShopApi.UserOutputDto[];
      };
    };
    '/users/{username}': {
      GET: {
        params: {
          username: string;
        };
        response: EShopApi.UserOutputDto;
      };
    };
    '/devices/{id}': {
      GET: {
        params: {
          id: number;
        };
        response: EShopApi.DeviceOutputDto;
      };
    };
    '/files/{id}': {
      DELETE: {
        params: {
          id: number;
        };
      };
    };
  };
}

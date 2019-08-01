import { Injectable } from '@angular/core';

@Injectable()
export class StorageService {

  private localStorage: Storage;

  constructor(private windowLocalStorage: WindowLocalStorage) {
    this.localStorage = this.windowLocalStorage.localStorage;
  }

  saveStringItem(key: string, data: string) {
    this.localStorage.setItem(key, data);
  }

  saveObjectItem(key: string, data: Object) {
    this.localStorage.setItem(key, JSON.stringify(data));
  }

  getStringItem(key: string): string | null {
    return this.localStorage.getItem(key);
  }

  getObjectItem(key: string): any | null {
    let objString: string | null = this.localStorage.getItem(key);
    if (objString) {
      return JSON.parse(objString);
    }
    return null;
  }
}

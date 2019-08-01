import { Injectable } from '@angular/core';

@Injectable()
export class StorageService {

  saveStringItem(key: string, data: string) {
    localStorage.setItem(key, data);
  }

  saveObjectItem(key: string, data: Object) {
    localStorage.setItem(key, JSON.stringify(data));
  }

  getStringItem(key: string): string | null {
    return localStorage.getItem(key);
  }

  getObjectItem(key: string): any | null {
    let objString: string | null = localStorage.getItem(key);
    if (objString) {
      return JSON.parse(objString);
    }
    return null;
  }

  removeItem(key:string) {
    localStorage.removeItem(key);
  }
}

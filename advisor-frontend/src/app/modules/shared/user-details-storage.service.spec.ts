import { TestBed } from '@angular/core/testing';

import { UserDetailsStorageService } from './user-details-storage.service';

describe('UserDetailsStorageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserDetailsStorageService = TestBed.get(UserDetailsStorageService);
    expect(service).toBeTruthy();
  });
});

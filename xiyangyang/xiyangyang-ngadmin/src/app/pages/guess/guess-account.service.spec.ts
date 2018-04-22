import { TestBed, inject } from '@angular/core/testing';

import { GuessAccountService } from './guess-account.service';

describe('GuessAccountService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GuessAccountService]
    });
  });

  it('should be created', inject([GuessAccountService], (service: GuessAccountService) => {
    expect(service).toBeTruthy();
  }));
});

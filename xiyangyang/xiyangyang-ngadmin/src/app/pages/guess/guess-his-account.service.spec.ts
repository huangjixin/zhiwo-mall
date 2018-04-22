import { TestBed, inject } from '@angular/core/testing';

import { GuessHisAccountService } from './guess-his-account.service';

describe('GuessHisAccountService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GuessHisAccountService]
    });
  });

  it('should be created', inject([GuessHisAccountService], (service: GuessHisAccountService) => {
    expect(service).toBeTruthy();
  }));
});

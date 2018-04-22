import { TestBed, inject } from '@angular/core/testing';

import { GuessCategoryService } from './guess-category.service';

describe('GuessCategoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GuessCategoryService]
    });
  });

  it('should be created', inject([GuessCategoryService], (service: GuessCategoryService) => {
    expect(service).toBeTruthy();
  }));
});

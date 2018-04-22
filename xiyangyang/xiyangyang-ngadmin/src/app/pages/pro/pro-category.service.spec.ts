import { TestBed, inject } from '@angular/core/testing';

import { ProCategoryService } from './pro-category.service';

describe('ProCategoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ProCategoryService]
    });
  });

  it('should be created', inject([ProCategoryService], (service: ProCategoryService) => {
    expect(service).toBeTruthy();
  }));
});

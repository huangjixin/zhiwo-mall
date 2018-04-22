import { TestBed, inject } from '@angular/core/testing';

import { MemCategoryService } from './mem-category.service';

describe('MemCategoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MemCategoryService]
    });
  });

  it('should be created', inject([MemCategoryService], (service: MemCategoryService) => {
    expect(service).toBeTruthy();
  }));
});

import { TestBed, inject } from '@angular/core/testing';

import { CmsCategoryService } from './cms-category.service';

describe('CmsCategoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CmsCategoryService]
    });
  });

  it('should be created', inject([CmsCategoryService], (service: CmsCategoryService) => {
    expect(service).toBeTruthy();
  }));
});

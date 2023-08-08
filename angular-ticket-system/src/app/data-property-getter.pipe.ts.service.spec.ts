import { TestBed } from '@angular/core/testing';

import { DataPropertyGetterPipeTsService } from './data-property-getter.pipe.ts.service';

describe('DataPropertyGetterPipeTsService', () => {
  let service: DataPropertyGetterPipeTsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataPropertyGetterPipeTsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

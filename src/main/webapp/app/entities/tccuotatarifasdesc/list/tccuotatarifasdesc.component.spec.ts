import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCCUOTATARIFASDESCService } from '../service/tccuotatarifasdesc.service';

import { TCCUOTATARIFASDESCComponent } from './tccuotatarifasdesc.component';

describe('Component Tests', () => {
  describe('TCCUOTATARIFASDESC Management Component', () => {
    let comp: TCCUOTATARIFASDESCComponent;
    let fixture: ComponentFixture<TCCUOTATARIFASDESCComponent>;
    let service: TCCUOTATARIFASDESCService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCUOTATARIFASDESCComponent],
      })
        .overrideTemplate(TCCUOTATARIFASDESCComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCUOTATARIFASDESCComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCCUOTATARIFASDESCService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idCuotaTarifaSdesc: 123 }],
            headers,
          })
        )
      );
    });

    it('Should call load all on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tCCUOTATARIFASDESCS?.[0]).toEqual(jasmine.objectContaining({ idCuotaTarifaSdesc: 123 }));
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCCOVIDTARIFASService } from '../service/tccovidtarifas.service';

import { TCCOVIDTARIFASComponent } from './tccovidtarifas.component';

describe('Component Tests', () => {
  describe('TCCOVIDTARIFAS Management Component', () => {
    let comp: TCCOVIDTARIFASComponent;
    let fixture: ComponentFixture<TCCOVIDTARIFASComponent>;
    let service: TCCOVIDTARIFASService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCOVIDTARIFASComponent],
      })
        .overrideTemplate(TCCOVIDTARIFASComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCOVIDTARIFASComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCCOVIDTARIFASService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idCovidTarifas: 123 }],
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
      expect(comp.tCCOVIDTARIFAS?.[0]).toEqual(jasmine.objectContaining({ idCovidTarifas: 123 }));
    });
  });
});

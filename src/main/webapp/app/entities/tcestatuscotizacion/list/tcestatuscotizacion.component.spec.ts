import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCESTATUSCOTIZACIONService } from '../service/tcestatuscotizacion.service';

import { TCESTATUSCOTIZACIONComponent } from './tcestatuscotizacion.component';

describe('Component Tests', () => {
  describe('TCESTATUSCOTIZACION Management Component', () => {
    let comp: TCESTATUSCOTIZACIONComponent;
    let fixture: ComponentFixture<TCESTATUSCOTIZACIONComponent>;
    let service: TCESTATUSCOTIZACIONService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCESTATUSCOTIZACIONComponent],
      })
        .overrideTemplate(TCESTATUSCOTIZACIONComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCESTATUSCOTIZACIONComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCESTATUSCOTIZACIONService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idEstatusCotizacion: 123 }],
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
      expect(comp.tCESTATUSCOTIZACIONS?.[0]).toEqual(jasmine.objectContaining({ idEstatusCotizacion: 123 }));
    });
  });
});

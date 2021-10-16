import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TMCOTIZACIONService } from '../service/tmcotizacion.service';

import { TMCOTIZACIONComponent } from './tmcotizacion.component';

describe('Component Tests', () => {
  describe('TMCOTIZACION Management Component', () => {
    let comp: TMCOTIZACIONComponent;
    let fixture: ComponentFixture<TMCOTIZACIONComponent>;
    let service: TMCOTIZACIONService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TMCOTIZACIONComponent],
      })
        .overrideTemplate(TMCOTIZACIONComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TMCOTIZACIONComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TMCOTIZACIONService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idCotizacion: 123 }],
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
      expect(comp.tMCOTIZACIONS?.[0]).toEqual(jasmine.objectContaining({ idCotizacion: 123 }));
    });
  });
});

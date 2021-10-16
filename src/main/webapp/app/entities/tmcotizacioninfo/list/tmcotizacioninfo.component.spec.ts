import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TMCOTIZACIONINFOService } from '../service/tmcotizacioninfo.service';

import { TMCOTIZACIONINFOComponent } from './tmcotizacioninfo.component';

describe('Component Tests', () => {
  describe('TMCOTIZACIONINFO Management Component', () => {
    let comp: TMCOTIZACIONINFOComponent;
    let fixture: ComponentFixture<TMCOTIZACIONINFOComponent>;
    let service: TMCOTIZACIONINFOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TMCOTIZACIONINFOComponent],
      })
        .overrideTemplate(TMCOTIZACIONINFOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TMCOTIZACIONINFOComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TMCOTIZACIONINFOService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idCotizacionInfo: 123 }],
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
      expect(comp.tMCOTIZACIONINFOS?.[0]).toEqual(jasmine.objectContaining({ idCotizacionInfo: 123 }));
    });
  });
});

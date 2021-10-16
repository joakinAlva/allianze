import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TMCOTIZACIONEXPPROPIAService } from '../service/tmcotizacionexppropia.service';

import { TMCOTIZACIONEXPPROPIAComponent } from './tmcotizacionexppropia.component';

describe('Component Tests', () => {
  describe('TMCOTIZACIONEXPPROPIA Management Component', () => {
    let comp: TMCOTIZACIONEXPPROPIAComponent;
    let fixture: ComponentFixture<TMCOTIZACIONEXPPROPIAComponent>;
    let service: TMCOTIZACIONEXPPROPIAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TMCOTIZACIONEXPPROPIAComponent],
      })
        .overrideTemplate(TMCOTIZACIONEXPPROPIAComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TMCOTIZACIONEXPPROPIAComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TMCOTIZACIONEXPPROPIAService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idCotizacionExpPropia: 123 }],
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
      expect(comp.tMCOTIZACIONEXPPROPIAS?.[0]).toEqual(jasmine.objectContaining({ idCotizacionExpPropia: 123 }));
    });
  });
});

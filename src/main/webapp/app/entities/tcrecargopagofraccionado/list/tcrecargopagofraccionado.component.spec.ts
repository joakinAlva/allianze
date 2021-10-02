import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCRECARGOPAGOFRACCIONADOService } from '../service/tcrecargopagofraccionado.service';

import { TCRECARGOPAGOFRACCIONADOComponent } from './tcrecargopagofraccionado.component';

describe('Component Tests', () => {
  describe('TCRECARGOPAGOFRACCIONADO Management Component', () => {
    let comp: TCRECARGOPAGOFRACCIONADOComponent;
    let fixture: ComponentFixture<TCRECARGOPAGOFRACCIONADOComponent>;
    let service: TCRECARGOPAGOFRACCIONADOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCRECARGOPAGOFRACCIONADOComponent],
      })
        .overrideTemplate(TCRECARGOPAGOFRACCIONADOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCRECARGOPAGOFRACCIONADOComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCRECARGOPAGOFRACCIONADOService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idRecargoPagoFraccionado: 123 }],
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
      expect(comp.tCRECARGOPAGOFRACCIONADOS?.[0]).toEqual(jasmine.objectContaining({ idRecargoPagoFraccionado: 123 }));
    });
  });
});

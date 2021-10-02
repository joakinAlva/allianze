import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCFACTORDESCUENTOService } from '../service/tcfactordescuento.service';

import { TCFACTORDESCUENTOComponent } from './tcfactordescuento.component';

describe('Component Tests', () => {
  describe('TCFACTORDESCUENTO Management Component', () => {
    let comp: TCFACTORDESCUENTOComponent;
    let fixture: ComponentFixture<TCFACTORDESCUENTOComponent>;
    let service: TCFACTORDESCUENTOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCFACTORDESCUENTOComponent],
      })
        .overrideTemplate(TCFACTORDESCUENTOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCFACTORDESCUENTOComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCFACTORDESCUENTOService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idFactorDescuento: 123 }],
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
      expect(comp.tCFACTORDESCUENTOS?.[0]).toEqual(jasmine.objectContaining({ idFactorDescuento: 123 }));
    });
  });
});

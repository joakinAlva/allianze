import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCOCUPACIONService } from '../service/tcocupacion.service';

import { TCOCUPACIONComponent } from './tcocupacion.component';

describe('Component Tests', () => {
  describe('TCOCUPACION Management Component', () => {
    let comp: TCOCUPACIONComponent;
    let fixture: ComponentFixture<TCOCUPACIONComponent>;
    let service: TCOCUPACIONService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCOCUPACIONComponent],
      })
        .overrideTemplate(TCOCUPACIONComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCOCUPACIONComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCOCUPACIONService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idOcupacion: 123 }],
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
      expect(comp.tCOCUPACIONS?.[0]).toEqual(jasmine.objectContaining({ idOcupacion: 123 }));
    });
  });
});

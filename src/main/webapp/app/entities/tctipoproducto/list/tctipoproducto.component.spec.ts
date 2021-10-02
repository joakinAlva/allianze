import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCTIPOPRODUCTOService } from '../service/tctipoproducto.service';

import { TCTIPOPRODUCTOComponent } from './tctipoproducto.component';

describe('Component Tests', () => {
  describe('TCTIPOPRODUCTO Management Component', () => {
    let comp: TCTIPOPRODUCTOComponent;
    let fixture: ComponentFixture<TCTIPOPRODUCTOComponent>;
    let service: TCTIPOPRODUCTOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCTIPOPRODUCTOComponent],
      })
        .overrideTemplate(TCTIPOPRODUCTOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCTIPOPRODUCTOComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCTIPOPRODUCTOService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idTipoProducto: 123 }],
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
      expect(comp.tCTIPOPRODUCTOS?.[0]).toEqual(jasmine.objectContaining({ idTipoProducto: 123 }));
    });
  });
});

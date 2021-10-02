import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCDESCUENTOSUMAASEGURADAService } from '../service/tcdescuentosumaasegurada.service';

import { TCDESCUENTOSUMAASEGURADAComponent } from './tcdescuentosumaasegurada.component';

describe('Component Tests', () => {
  describe('TCDESCUENTOSUMAASEGURADA Management Component', () => {
    let comp: TCDESCUENTOSUMAASEGURADAComponent;
    let fixture: ComponentFixture<TCDESCUENTOSUMAASEGURADAComponent>;
    let service: TCDESCUENTOSUMAASEGURADAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCDESCUENTOSUMAASEGURADAComponent],
      })
        .overrideTemplate(TCDESCUENTOSUMAASEGURADAComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCDESCUENTOSUMAASEGURADAComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCDESCUENTOSUMAASEGURADAService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idDescuentoSumaAsegurada: 123 }],
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
      expect(comp.tCDESCUENTOSUMAASEGURADAS?.[0]).toEqual(jasmine.objectContaining({ idDescuentoSumaAsegurada: 123 }));
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCUNIDADNEGOCIOService } from '../service/tcunidadnegocio.service';

import { TCUNIDADNEGOCIOComponent } from './tcunidadnegocio.component';

describe('Component Tests', () => {
  describe('TCUNIDADNEGOCIO Management Component', () => {
    let comp: TCUNIDADNEGOCIOComponent;
    let fixture: ComponentFixture<TCUNIDADNEGOCIOComponent>;
    let service: TCUNIDADNEGOCIOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCUNIDADNEGOCIOComponent],
      })
        .overrideTemplate(TCUNIDADNEGOCIOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCUNIDADNEGOCIOComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCUNIDADNEGOCIOService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idUnidadNegocio: 123 }],
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
      expect(comp.tCUNIDADNEGOCIOS?.[0]).toEqual(jasmine.objectContaining({ idUnidadNegocio: 123 }));
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCPERFILService } from '../service/tcperfil.service';

import { TCPERFILComponent } from './tcperfil.component';

describe('Component Tests', () => {
  describe('TCPERFIL Management Component', () => {
    let comp: TCPERFILComponent;
    let fixture: ComponentFixture<TCPERFILComponent>;
    let service: TCPERFILService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCPERFILComponent],
      })
        .overrideTemplate(TCPERFILComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCPERFILComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCPERFILService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idPerfil: 123 }],
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
      expect(comp.tCPERFILS?.[0]).toEqual(jasmine.objectContaining({ idPerfil: 123 }));
    });
  });
});

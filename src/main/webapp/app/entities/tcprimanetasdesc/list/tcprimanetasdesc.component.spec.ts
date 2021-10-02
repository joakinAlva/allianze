import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCPRIMANETASDESCService } from '../service/tcprimanetasdesc.service';

import { TCPRIMANETASDESCComponent } from './tcprimanetasdesc.component';

describe('Component Tests', () => {
  describe('TCPRIMANETASDESC Management Component', () => {
    let comp: TCPRIMANETASDESCComponent;
    let fixture: ComponentFixture<TCPRIMANETASDESCComponent>;
    let service: TCPRIMANETASDESCService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCPRIMANETASDESCComponent],
      })
        .overrideTemplate(TCPRIMANETASDESCComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCPRIMANETASDESCComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCPRIMANETASDESCService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idPrimaNetaSdesc: 123 }],
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
      expect(comp.tCPRIMANETASDESCS?.[0]).toEqual(jasmine.objectContaining({ idPrimaNetaSdesc: 123 }));
    });
  });
});

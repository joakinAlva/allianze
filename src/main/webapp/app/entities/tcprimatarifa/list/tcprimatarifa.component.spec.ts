import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCPRIMATARIFAService } from '../service/tcprimatarifa.service';

import { TCPRIMATARIFAComponent } from './tcprimatarifa.component';

describe('Component Tests', () => {
  describe('TCPRIMATARIFA Management Component', () => {
    let comp: TCPRIMATARIFAComponent;
    let fixture: ComponentFixture<TCPRIMATARIFAComponent>;
    let service: TCPRIMATARIFAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCPRIMATARIFAComponent],
      })
        .overrideTemplate(TCPRIMATARIFAComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCPRIMATARIFAComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCPRIMATARIFAService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idPrimaTarifa: 123 }],
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
      expect(comp.tCPRIMATARIFAS?.[0]).toEqual(jasmine.objectContaining({ idPrimaTarifa: 123 }));
    });
  });
});

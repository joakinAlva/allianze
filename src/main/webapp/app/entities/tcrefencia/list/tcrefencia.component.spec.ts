import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCREFENCIAService } from '../service/tcrefencia.service';

import { TCREFENCIAComponent } from './tcrefencia.component';

describe('Component Tests', () => {
  describe('TCREFENCIA Management Component', () => {
    let comp: TCREFENCIAComponent;
    let fixture: ComponentFixture<TCREFENCIAComponent>;
    let service: TCREFENCIAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCREFENCIAComponent],
      })
        .overrideTemplate(TCREFENCIAComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCREFENCIAComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCREFENCIAService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idReferencia: 123 }],
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
      expect(comp.tCREFENCIAS?.[0]).toEqual(jasmine.objectContaining({ idReferencia: 123 }));
    });
  });
});

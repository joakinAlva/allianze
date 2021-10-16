import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TMASEGURADOService } from '../service/tmasegurado.service';

import { TMASEGURADOComponent } from './tmasegurado.component';

describe('Component Tests', () => {
  describe('TMASEGURADO Management Component', () => {
    let comp: TMASEGURADOComponent;
    let fixture: ComponentFixture<TMASEGURADOComponent>;
    let service: TMASEGURADOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TMASEGURADOComponent],
      })
        .overrideTemplate(TMASEGURADOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TMASEGURADOComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TMASEGURADOService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idAsegurados: 123 }],
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
      expect(comp.tMASEGURADOS?.[0]).toEqual(jasmine.objectContaining({ idAsegurados: 123 }));
    });
  });
});

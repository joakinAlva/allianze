import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCTIPOService } from '../service/tctipo.service';

import { TCTIPOComponent } from './tctipo.component';

describe('Component Tests', () => {
  describe('TCTIPO Management Component', () => {
    let comp: TCTIPOComponent;
    let fixture: ComponentFixture<TCTIPOComponent>;
    let service: TCTIPOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCTIPOComponent],
      })
        .overrideTemplate(TCTIPOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCTIPOComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCTIPOService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idTipo: 123 }],
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
      expect(comp.tCTIPOS?.[0]).toEqual(jasmine.objectContaining({ idTipo: 123 }));
    });
  });
});

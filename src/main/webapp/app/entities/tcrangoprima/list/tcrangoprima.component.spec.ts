import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCRANGOPRIMAService } from '../service/tcrangoprima.service';

import { TCRANGOPRIMAComponent } from './tcrangoprima.component';

describe('Component Tests', () => {
  describe('TCRANGOPRIMA Management Component', () => {
    let comp: TCRANGOPRIMAComponent;
    let fixture: ComponentFixture<TCRANGOPRIMAComponent>;
    let service: TCRANGOPRIMAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCRANGOPRIMAComponent],
      })
        .overrideTemplate(TCRANGOPRIMAComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCRANGOPRIMAComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCRANGOPRIMAService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idRangoPrima: 123 }],
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
      expect(comp.tCRANGOPRIMAS?.[0]).toEqual(jasmine.objectContaining({ idRangoPrima: 123 }));
    });
  });
});

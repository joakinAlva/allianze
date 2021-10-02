import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCHIPOTESISService } from '../service/tchipotesis.service';

import { TCHIPOTESISComponent } from './tchipotesis.component';

describe('Component Tests', () => {
  describe('TCHIPOTESIS Management Component', () => {
    let comp: TCHIPOTESISComponent;
    let fixture: ComponentFixture<TCHIPOTESISComponent>;
    let service: TCHIPOTESISService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCHIPOTESISComponent],
      })
        .overrideTemplate(TCHIPOTESISComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCHIPOTESISComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCHIPOTESISService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idHipotesis: 123 }],
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
      expect(comp.tCHIPOTESES?.[0]).toEqual(jasmine.objectContaining({ idHipotesis: 123 }));
    });
  });
});

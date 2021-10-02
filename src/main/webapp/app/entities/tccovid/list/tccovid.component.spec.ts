import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCCOVIDService } from '../service/tccovid.service';

import { TCCOVIDComponent } from './tccovid.component';

describe('Component Tests', () => {
  describe('TCCOVID Management Component', () => {
    let comp: TCCOVIDComponent;
    let fixture: ComponentFixture<TCCOVIDComponent>;
    let service: TCCOVIDService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCOVIDComponent],
      })
        .overrideTemplate(TCCOVIDComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCOVIDComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCCOVIDService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idCovid: 123 }],
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
      expect(comp.tCCOVIDS?.[0]).toEqual(jasmine.objectContaining({ idCovid: 123 }));
    });
  });
});

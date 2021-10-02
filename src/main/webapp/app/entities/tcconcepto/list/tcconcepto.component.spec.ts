import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCCONCEPTOService } from '../service/tcconcepto.service';

import { TCCONCEPTOComponent } from './tcconcepto.component';

describe('Component Tests', () => {
  describe('TCCONCEPTO Management Component', () => {
    let comp: TCCONCEPTOComponent;
    let fixture: ComponentFixture<TCCONCEPTOComponent>;
    let service: TCCONCEPTOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCONCEPTOComponent],
      })
        .overrideTemplate(TCCONCEPTOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCONCEPTOComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCCONCEPTOService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idConcepto: 123 }],
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
      expect(comp.tCCONCEPTOS?.[0]).toEqual(jasmine.objectContaining({ idConcepto: 123 }));
    });
  });
});

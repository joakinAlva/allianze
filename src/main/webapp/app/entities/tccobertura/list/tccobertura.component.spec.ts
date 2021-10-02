import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCCOBERTURAService } from '../service/tccobertura.service';

import { TCCOBERTURAComponent } from './tccobertura.component';

describe('Component Tests', () => {
  describe('TCCOBERTURA Management Component', () => {
    let comp: TCCOBERTURAComponent;
    let fixture: ComponentFixture<TCCOBERTURAComponent>;
    let service: TCCOBERTURAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCOBERTURAComponent],
      })
        .overrideTemplate(TCCOBERTURAComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCOBERTURAComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCCOBERTURAService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idCobertura: 123 }],
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
      expect(comp.tCCOBERTURAS?.[0]).toEqual(jasmine.objectContaining({ idCobertura: 123 }));
    });
  });
});

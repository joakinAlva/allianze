import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCFACTORSAMIService } from '../service/tcfactorsami.service';

import { TCFACTORSAMIComponent } from './tcfactorsami.component';

describe('Component Tests', () => {
  describe('TCFACTORSAMI Management Component', () => {
    let comp: TCFACTORSAMIComponent;
    let fixture: ComponentFixture<TCFACTORSAMIComponent>;
    let service: TCFACTORSAMIService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCFACTORSAMIComponent],
      })
        .overrideTemplate(TCFACTORSAMIComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCFACTORSAMIComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCFACTORSAMIService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idFactorSami: 123 }],
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
      expect(comp.tCFACTORSAMIS?.[0]).toEqual(jasmine.objectContaining({ idFactorSami: 123 }));
    });
  });
});

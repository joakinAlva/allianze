import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCEDADRECARGOService } from '../service/tcedadrecargo.service';

import { TCEDADRECARGOComponent } from './tcedadrecargo.component';

describe('Component Tests', () => {
  describe('TCEDADRECARGO Management Component', () => {
    let comp: TCEDADRECARGOComponent;
    let fixture: ComponentFixture<TCEDADRECARGOComponent>;
    let service: TCEDADRECARGOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCEDADRECARGOComponent],
      })
        .overrideTemplate(TCEDADRECARGOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCEDADRECARGOComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCEDADRECARGOService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idEdadRecargo: 123 }],
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
      expect(comp.tCEDADRECARGOS?.[0]).toEqual(jasmine.objectContaining({ idEdadRecargo: 123 }));
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCEJECUTIVOService } from '../service/tcejecutivo.service';

import { TCEJECUTIVOComponent } from './tcejecutivo.component';

describe('Component Tests', () => {
  describe('TCEJECUTIVO Management Component', () => {
    let comp: TCEJECUTIVOComponent;
    let fixture: ComponentFixture<TCEJECUTIVOComponent>;
    let service: TCEJECUTIVOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCEJECUTIVOComponent],
      })
        .overrideTemplate(TCEJECUTIVOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCEJECUTIVOComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCEJECUTIVOService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idEjecutivo: 123 }],
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
      expect(comp.tCEJECUTIVOS?.[0]).toEqual(jasmine.objectContaining({ idEjecutivo: 123 }));
    });
  });
});

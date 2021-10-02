import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCSUBGRUPOService } from '../service/tcsubgrupo.service';

import { TCSUBGRUPOComponent } from './tcsubgrupo.component';

describe('Component Tests', () => {
  describe('TCSUBGRUPO Management Component', () => {
    let comp: TCSUBGRUPOComponent;
    let fixture: ComponentFixture<TCSUBGRUPOComponent>;
    let service: TCSUBGRUPOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCSUBGRUPOComponent],
      })
        .overrideTemplate(TCSUBGRUPOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCSUBGRUPOComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCSUBGRUPOService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idSubGrupo: 123 }],
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
      expect(comp.tCSUBGRUPOS?.[0]).toEqual(jasmine.objectContaining({ idSubGrupo: 123 }));
    });
  });
});

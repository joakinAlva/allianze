import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCSUMAASEGURADAService } from '../service/tcsumaasegurada.service';

import { TCSUMAASEGURADAComponent } from './tcsumaasegurada.component';

describe('Component Tests', () => {
  describe('TCSUMAASEGURADA Management Component', () => {
    let comp: TCSUMAASEGURADAComponent;
    let fixture: ComponentFixture<TCSUMAASEGURADAComponent>;
    let service: TCSUMAASEGURADAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCSUMAASEGURADAComponent],
      })
        .overrideTemplate(TCSUMAASEGURADAComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCSUMAASEGURADAComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCSUMAASEGURADAService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idSumaAsegurada: 123 }],
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
      expect(comp.tCSUMAASEGURADAS?.[0]).toEqual(jasmine.objectContaining({ idSumaAsegurada: 123 }));
    });
  });
});

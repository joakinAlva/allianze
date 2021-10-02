import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCCUOTAVALORService } from '../service/tccuotavalor.service';

import { TCCUOTAVALORComponent } from './tccuotavalor.component';

describe('Component Tests', () => {
  describe('TCCUOTAVALOR Management Component', () => {
    let comp: TCCUOTAVALORComponent;
    let fixture: ComponentFixture<TCCUOTAVALORComponent>;
    let service: TCCUOTAVALORService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCUOTAVALORComponent],
      })
        .overrideTemplate(TCCUOTAVALORComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCUOTAVALORComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCCUOTAVALORService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idCuotaValor: 123 }],
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
      expect(comp.tCCUOTAVALORS?.[0]).toEqual(jasmine.objectContaining({ idCuotaValor: 123 }));
    });
  });
});

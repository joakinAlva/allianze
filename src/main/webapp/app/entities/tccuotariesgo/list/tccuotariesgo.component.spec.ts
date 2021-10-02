import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCCUOTARIESGOService } from '../service/tccuotariesgo.service';

import { TCCUOTARIESGOComponent } from './tccuotariesgo.component';

describe('Component Tests', () => {
  describe('TCCUOTARIESGO Management Component', () => {
    let comp: TCCUOTARIESGOComponent;
    let fixture: ComponentFixture<TCCUOTARIESGOComponent>;
    let service: TCCUOTARIESGOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCUOTARIESGOComponent],
      })
        .overrideTemplate(TCCUOTARIESGOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCUOTARIESGOComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCCUOTARIESGOService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idCuotaRiesgo: 123 }],
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
      expect(comp.tCCUOTARIESGOS?.[0]).toEqual(jasmine.objectContaining({ idCuotaRiesgo: 123 }));
    });
  });
});

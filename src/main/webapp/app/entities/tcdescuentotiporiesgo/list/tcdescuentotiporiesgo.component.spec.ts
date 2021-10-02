import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCDESCUENTOTIPORIESGOService } from '../service/tcdescuentotiporiesgo.service';

import { TCDESCUENTOTIPORIESGOComponent } from './tcdescuentotiporiesgo.component';

describe('Component Tests', () => {
  describe('TCDESCUENTOTIPORIESGO Management Component', () => {
    let comp: TCDESCUENTOTIPORIESGOComponent;
    let fixture: ComponentFixture<TCDESCUENTOTIPORIESGOComponent>;
    let service: TCDESCUENTOTIPORIESGOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCDESCUENTOTIPORIESGOComponent],
      })
        .overrideTemplate(TCDESCUENTOTIPORIESGOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCDESCUENTOTIPORIESGOComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCDESCUENTOTIPORIESGOService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idDescuentoTipoRiesgo: 123 }],
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
      expect(comp.tCDESCUENTOTIPORIESGOS?.[0]).toEqual(jasmine.objectContaining({ idDescuentoTipoRiesgo: 123 }));
    });
  });
});

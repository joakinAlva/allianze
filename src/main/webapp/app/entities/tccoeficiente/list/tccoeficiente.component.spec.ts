import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCCOEFICIENTEService } from '../service/tccoeficiente.service';

import { TCCOEFICIENTEComponent } from './tccoeficiente.component';

describe('Component Tests', () => {
  describe('TCCOEFICIENTE Management Component', () => {
    let comp: TCCOEFICIENTEComponent;
    let fixture: ComponentFixture<TCCOEFICIENTEComponent>;
    let service: TCCOEFICIENTEService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCOEFICIENTEComponent],
      })
        .overrideTemplate(TCCOEFICIENTEComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCOEFICIENTEComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCCOEFICIENTEService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idCoeficiente: 123 }],
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
      expect(comp.tCCOEFICIENTES?.[0]).toEqual(jasmine.objectContaining({ idCoeficiente: 123 }));
    });
  });
});

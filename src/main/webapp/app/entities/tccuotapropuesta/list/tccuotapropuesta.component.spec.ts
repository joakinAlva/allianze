import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCCUOTAPROPUESTAService } from '../service/tccuotapropuesta.service';

import { TCCUOTAPROPUESTAComponent } from './tccuotapropuesta.component';

describe('Component Tests', () => {
  describe('TCCUOTAPROPUESTA Management Component', () => {
    let comp: TCCUOTAPROPUESTAComponent;
    let fixture: ComponentFixture<TCCUOTAPROPUESTAComponent>;
    let service: TCCUOTAPROPUESTAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCUOTAPROPUESTAComponent],
      })
        .overrideTemplate(TCCUOTAPROPUESTAComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCUOTAPROPUESTAComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCCUOTAPROPUESTAService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idCuotaPropuesta: 123 }],
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
      expect(comp.tCCUOTAPROPUESTAS?.[0]).toEqual(jasmine.objectContaining({ idCuotaPropuesta: 123 }));
    });
  });
});

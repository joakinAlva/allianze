import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCTIPOREGLAService } from '../service/tctiporegla.service';

import { TCTIPOREGLAComponent } from './tctiporegla.component';

describe('Component Tests', () => {
  describe('TCTIPOREGLA Management Component', () => {
    let comp: TCTIPOREGLAComponent;
    let fixture: ComponentFixture<TCTIPOREGLAComponent>;
    let service: TCTIPOREGLAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCTIPOREGLAComponent],
      })
        .overrideTemplate(TCTIPOREGLAComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCTIPOREGLAComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCTIPOREGLAService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idTipoRegla: 123 }],
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
      expect(comp.tCTIPOREGLAS?.[0]).toEqual(jasmine.objectContaining({ idTipoRegla: 123 }));
    });
  });
});

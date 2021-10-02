import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TMUSUARIOService } from '../service/tmusuario.service';

import { TMUSUARIOComponent } from './tmusuario.component';

describe('Component Tests', () => {
  describe('TMUSUARIO Management Component', () => {
    let comp: TMUSUARIOComponent;
    let fixture: ComponentFixture<TMUSUARIOComponent>;
    let service: TMUSUARIOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TMUSUARIOComponent],
      })
        .overrideTemplate(TMUSUARIOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TMUSUARIOComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TMUSUARIOService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idUsuario: 123 }],
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
      expect(comp.tMUSUARIOS?.[0]).toEqual(jasmine.objectContaining({ idUsuario: 123 }));
    });
  });
});

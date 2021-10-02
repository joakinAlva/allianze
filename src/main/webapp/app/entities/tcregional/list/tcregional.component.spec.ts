import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TCREGIONALService } from '../service/tcregional.service';

import { TCREGIONALComponent } from './tcregional.component';

describe('Component Tests', () => {
  describe('TCREGIONAL Management Component', () => {
    let comp: TCREGIONALComponent;
    let fixture: ComponentFixture<TCREGIONALComponent>;
    let service: TCREGIONALService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCREGIONALComponent],
      })
        .overrideTemplate(TCREGIONALComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCREGIONALComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCREGIONALService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ idRegional: 123 }],
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
      expect(comp.tCREGIONALS?.[0]).toEqual(jasmine.objectContaining({ idRegional: 123 }));
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TMCOTIZACIONINFODetailComponent } from './tmcotizacioninfo-detail.component';

describe('Component Tests', () => {
  describe('TMCOTIZACIONINFO Management Detail Component', () => {
    let comp: TMCOTIZACIONINFODetailComponent;
    let fixture: ComponentFixture<TMCOTIZACIONINFODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TMCOTIZACIONINFODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tMCOTIZACIONINFO: { idCotizacionInfo: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TMCOTIZACIONINFODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TMCOTIZACIONINFODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tMCOTIZACIONINFO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tMCOTIZACIONINFO).toEqual(jasmine.objectContaining({ idCotizacionInfo: 123 }));
      });
    });
  });
});

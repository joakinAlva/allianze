import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TMCOTIZACIONDetailComponent } from './tmcotizacion-detail.component';

describe('Component Tests', () => {
  describe('TMCOTIZACION Management Detail Component', () => {
    let comp: TMCOTIZACIONDetailComponent;
    let fixture: ComponentFixture<TMCOTIZACIONDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TMCOTIZACIONDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tMCOTIZACION: { idCotizacion: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TMCOTIZACIONDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TMCOTIZACIONDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tMCOTIZACION on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tMCOTIZACION).toEqual(jasmine.objectContaining({ idCotizacion: 123 }));
      });
    });
  });
});

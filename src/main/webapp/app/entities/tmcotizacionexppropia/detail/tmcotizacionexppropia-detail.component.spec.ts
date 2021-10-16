import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TMCOTIZACIONEXPPROPIADetailComponent } from './tmcotizacionexppropia-detail.component';

describe('Component Tests', () => {
  describe('TMCOTIZACIONEXPPROPIA Management Detail Component', () => {
    let comp: TMCOTIZACIONEXPPROPIADetailComponent;
    let fixture: ComponentFixture<TMCOTIZACIONEXPPROPIADetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TMCOTIZACIONEXPPROPIADetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tMCOTIZACIONEXPPROPIA: { idCotizacionExpPropia: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TMCOTIZACIONEXPPROPIADetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TMCOTIZACIONEXPPROPIADetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tMCOTIZACIONEXPPROPIA on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tMCOTIZACIONEXPPROPIA).toEqual(jasmine.objectContaining({ idCotizacionExpPropia: 123 }));
      });
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCESTATUSCOTIZACIONDetailComponent } from './tcestatuscotizacion-detail.component';

describe('Component Tests', () => {
  describe('TCESTATUSCOTIZACION Management Detail Component', () => {
    let comp: TCESTATUSCOTIZACIONDetailComponent;
    let fixture: ComponentFixture<TCESTATUSCOTIZACIONDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCESTATUSCOTIZACIONDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCESTATUSCOTIZACION: { idEstatusCotizacion: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCESTATUSCOTIZACIONDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCESTATUSCOTIZACIONDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCESTATUSCOTIZACION on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCESTATUSCOTIZACION).toEqual(jasmine.objectContaining({ idEstatusCotizacion: 123 }));
      });
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCRECARGOPAGOFRACCIONADODetailComponent } from './tcrecargopagofraccionado-detail.component';

describe('Component Tests', () => {
  describe('TCRECARGOPAGOFRACCIONADO Management Detail Component', () => {
    let comp: TCRECARGOPAGOFRACCIONADODetailComponent;
    let fixture: ComponentFixture<TCRECARGOPAGOFRACCIONADODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCRECARGOPAGOFRACCIONADODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCRECARGOPAGOFRACCIONADO: { idRecargoPagoFraccionado: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCRECARGOPAGOFRACCIONADODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCRECARGOPAGOFRACCIONADODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCRECARGOPAGOFRACCIONADO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCRECARGOPAGOFRACCIONADO).toEqual(jasmine.objectContaining({ idRecargoPagoFraccionado: 123 }));
      });
    });
  });
});

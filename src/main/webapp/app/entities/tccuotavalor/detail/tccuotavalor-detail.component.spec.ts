import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCCUOTAVALORDetailComponent } from './tccuotavalor-detail.component';

describe('Component Tests', () => {
  describe('TCCUOTAVALOR Management Detail Component', () => {
    let comp: TCCUOTAVALORDetailComponent;
    let fixture: ComponentFixture<TCCUOTAVALORDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCCUOTAVALORDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCCUOTAVALOR: { idCuotaValor: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCCUOTAVALORDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCCUOTAVALORDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCCUOTAVALOR on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCCUOTAVALOR).toEqual(jasmine.objectContaining({ idCuotaValor: 123 }));
      });
    });
  });
});

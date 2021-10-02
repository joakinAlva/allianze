import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCCUOTATARIFASDESCDetailComponent } from './tccuotatarifasdesc-detail.component';

describe('Component Tests', () => {
  describe('TCCUOTATARIFASDESC Management Detail Component', () => {
    let comp: TCCUOTATARIFASDESCDetailComponent;
    let fixture: ComponentFixture<TCCUOTATARIFASDESCDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCCUOTATARIFASDESCDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCCUOTATARIFASDESC: { idCuotaTarifaSdesc: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCCUOTATARIFASDESCDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCCUOTATARIFASDESCDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCCUOTATARIFASDESC on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCCUOTATARIFASDESC).toEqual(jasmine.objectContaining({ idCuotaTarifaSdesc: 123 }));
      });
    });
  });
});

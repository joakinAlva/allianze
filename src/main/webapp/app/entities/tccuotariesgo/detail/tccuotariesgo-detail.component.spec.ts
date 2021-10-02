import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCCUOTARIESGODetailComponent } from './tccuotariesgo-detail.component';

describe('Component Tests', () => {
  describe('TCCUOTARIESGO Management Detail Component', () => {
    let comp: TCCUOTARIESGODetailComponent;
    let fixture: ComponentFixture<TCCUOTARIESGODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCCUOTARIESGODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCCUOTARIESGO: { idCuotaRiesgo: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCCUOTARIESGODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCCUOTARIESGODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCCUOTARIESGO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCCUOTARIESGO).toEqual(jasmine.objectContaining({ idCuotaRiesgo: 123 }));
      });
    });
  });
});

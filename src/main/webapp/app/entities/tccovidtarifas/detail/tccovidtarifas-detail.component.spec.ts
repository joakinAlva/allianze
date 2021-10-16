import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCCOVIDTARIFASDetailComponent } from './tccovidtarifas-detail.component';

describe('Component Tests', () => {
  describe('TCCOVIDTARIFAS Management Detail Component', () => {
    let comp: TCCOVIDTARIFASDetailComponent;
    let fixture: ComponentFixture<TCCOVIDTARIFASDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCCOVIDTARIFASDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCCOVIDTARIFAS: { idCovidTarifas: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCCOVIDTARIFASDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCCOVIDTARIFASDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCCOVIDTARIFAS on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCCOVIDTARIFAS).toEqual(jasmine.objectContaining({ idCovidTarifas: 123 }));
      });
    });
  });
});

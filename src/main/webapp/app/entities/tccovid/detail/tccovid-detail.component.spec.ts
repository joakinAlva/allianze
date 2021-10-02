import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCCOVIDDetailComponent } from './tccovid-detail.component';

describe('Component Tests', () => {
  describe('TCCOVID Management Detail Component', () => {
    let comp: TCCOVIDDetailComponent;
    let fixture: ComponentFixture<TCCOVIDDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCCOVIDDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCCOVID: { idCovid: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCCOVIDDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCCOVIDDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCCOVID on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCCOVID).toEqual(jasmine.objectContaining({ idCovid: 123 }));
      });
    });
  });
});

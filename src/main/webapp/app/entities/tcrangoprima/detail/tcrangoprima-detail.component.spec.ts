import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCRANGOPRIMADetailComponent } from './tcrangoprima-detail.component';

describe('Component Tests', () => {
  describe('TCRANGOPRIMA Management Detail Component', () => {
    let comp: TCRANGOPRIMADetailComponent;
    let fixture: ComponentFixture<TCRANGOPRIMADetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCRANGOPRIMADetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCRANGOPRIMA: { idRangoPrima: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCRANGOPRIMADetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCRANGOPRIMADetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCRANGOPRIMA on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCRANGOPRIMA).toEqual(jasmine.objectContaining({ idRangoPrima: 123 }));
      });
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCCOEFICIENTEDetailComponent } from './tccoeficiente-detail.component';

describe('Component Tests', () => {
  describe('TCCOEFICIENTE Management Detail Component', () => {
    let comp: TCCOEFICIENTEDetailComponent;
    let fixture: ComponentFixture<TCCOEFICIENTEDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCCOEFICIENTEDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCCOEFICIENTE: { idCoeficiente: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCCOEFICIENTEDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCCOEFICIENTEDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCCOEFICIENTE on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCCOEFICIENTE).toEqual(jasmine.objectContaining({ idCoeficiente: 123 }));
      });
    });
  });
});

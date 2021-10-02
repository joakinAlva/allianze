import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCEDADRECARGODetailComponent } from './tcedadrecargo-detail.component';

describe('Component Tests', () => {
  describe('TCEDADRECARGO Management Detail Component', () => {
    let comp: TCEDADRECARGODetailComponent;
    let fixture: ComponentFixture<TCEDADRECARGODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCEDADRECARGODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCEDADRECARGO: { idEdadRecargo: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCEDADRECARGODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCEDADRECARGODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCEDADRECARGO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCEDADRECARGO).toEqual(jasmine.objectContaining({ idEdadRecargo: 123 }));
      });
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCFACTORSAMIDetailComponent } from './tcfactorsami-detail.component';

describe('Component Tests', () => {
  describe('TCFACTORSAMI Management Detail Component', () => {
    let comp: TCFACTORSAMIDetailComponent;
    let fixture: ComponentFixture<TCFACTORSAMIDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCFACTORSAMIDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCFACTORSAMI: { idFactorSami: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCFACTORSAMIDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCFACTORSAMIDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCFACTORSAMI on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCFACTORSAMI).toEqual(jasmine.objectContaining({ idFactorSami: 123 }));
      });
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCFACTORDESCUENTODetailComponent } from './tcfactordescuento-detail.component';

describe('Component Tests', () => {
  describe('TCFACTORDESCUENTO Management Detail Component', () => {
    let comp: TCFACTORDESCUENTODetailComponent;
    let fixture: ComponentFixture<TCFACTORDESCUENTODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCFACTORDESCUENTODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCFACTORDESCUENTO: { idFactorDescuento: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCFACTORDESCUENTODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCFACTORDESCUENTODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCFACTORDESCUENTO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCFACTORDESCUENTO).toEqual(jasmine.objectContaining({ idFactorDescuento: 123 }));
      });
    });
  });
});

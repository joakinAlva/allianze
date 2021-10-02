import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCOCUPACIONDetailComponent } from './tcocupacion-detail.component';

describe('Component Tests', () => {
  describe('TCOCUPACION Management Detail Component', () => {
    let comp: TCOCUPACIONDetailComponent;
    let fixture: ComponentFixture<TCOCUPACIONDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCOCUPACIONDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCOCUPACION: { idOcupacion: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCOCUPACIONDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCOCUPACIONDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCOCUPACION on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCOCUPACION).toEqual(jasmine.objectContaining({ idOcupacion: 123 }));
      });
    });
  });
});

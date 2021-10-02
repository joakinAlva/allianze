import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCCUOTAPROPUESTADetailComponent } from './tccuotapropuesta-detail.component';

describe('Component Tests', () => {
  describe('TCCUOTAPROPUESTA Management Detail Component', () => {
    let comp: TCCUOTAPROPUESTADetailComponent;
    let fixture: ComponentFixture<TCCUOTAPROPUESTADetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCCUOTAPROPUESTADetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCCUOTAPROPUESTA: { idCuotaPropuesta: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCCUOTAPROPUESTADetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCCUOTAPROPUESTADetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCCUOTAPROPUESTA on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCCUOTAPROPUESTA).toEqual(jasmine.objectContaining({ idCuotaPropuesta: 123 }));
      });
    });
  });
});

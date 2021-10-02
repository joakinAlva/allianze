import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCEJECUTIVODetailComponent } from './tcejecutivo-detail.component';

describe('Component Tests', () => {
  describe('TCEJECUTIVO Management Detail Component', () => {
    let comp: TCEJECUTIVODetailComponent;
    let fixture: ComponentFixture<TCEJECUTIVODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCEJECUTIVODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCEJECUTIVO: { idEjecutivo: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCEJECUTIVODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCEJECUTIVODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCEJECUTIVO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCEJECUTIVO).toEqual(jasmine.objectContaining({ idEjecutivo: 123 }));
      });
    });
  });
});

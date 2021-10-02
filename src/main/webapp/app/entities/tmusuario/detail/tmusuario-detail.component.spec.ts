import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TMUSUARIODetailComponent } from './tmusuario-detail.component';

describe('Component Tests', () => {
  describe('TMUSUARIO Management Detail Component', () => {
    let comp: TMUSUARIODetailComponent;
    let fixture: ComponentFixture<TMUSUARIODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TMUSUARIODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tMUSUARIO: { idUsuario: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TMUSUARIODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TMUSUARIODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tMUSUARIO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tMUSUARIO).toEqual(jasmine.objectContaining({ idUsuario: 123 }));
      });
    });
  });
});

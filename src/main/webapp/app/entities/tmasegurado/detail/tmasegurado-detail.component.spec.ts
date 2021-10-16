import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TMASEGURADODetailComponent } from './tmasegurado-detail.component';

describe('Component Tests', () => {
  describe('TMASEGURADO Management Detail Component', () => {
    let comp: TMASEGURADODetailComponent;
    let fixture: ComponentFixture<TMASEGURADODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TMASEGURADODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tMASEGURADO: { idAsegurados: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TMASEGURADODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TMASEGURADODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tMASEGURADO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tMASEGURADO).toEqual(jasmine.objectContaining({ idAsegurados: 123 }));
      });
    });
  });
});

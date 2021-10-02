import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCTIPODetailComponent } from './tctipo-detail.component';

describe('Component Tests', () => {
  describe('TCTIPO Management Detail Component', () => {
    let comp: TCTIPODetailComponent;
    let fixture: ComponentFixture<TCTIPODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCTIPODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCTIPO: { idTipo: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCTIPODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCTIPODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCTIPO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCTIPO).toEqual(jasmine.objectContaining({ idTipo: 123 }));
      });
    });
  });
});

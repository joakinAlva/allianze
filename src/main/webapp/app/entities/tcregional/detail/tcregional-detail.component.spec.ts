import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCREGIONALDetailComponent } from './tcregional-detail.component';

describe('Component Tests', () => {
  describe('TCREGIONAL Management Detail Component', () => {
    let comp: TCREGIONALDetailComponent;
    let fixture: ComponentFixture<TCREGIONALDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCREGIONALDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCREGIONAL: { idRegional: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCREGIONALDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCREGIONALDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCREGIONAL on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCREGIONAL).toEqual(jasmine.objectContaining({ idRegional: 123 }));
      });
    });
  });
});

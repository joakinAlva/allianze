import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCHIPOTESISDetailComponent } from './tchipotesis-detail.component';

describe('Component Tests', () => {
  describe('TCHIPOTESIS Management Detail Component', () => {
    let comp: TCHIPOTESISDetailComponent;
    let fixture: ComponentFixture<TCHIPOTESISDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCHIPOTESISDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCHIPOTESIS: { idHipotesis: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCHIPOTESISDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCHIPOTESISDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCHIPOTESIS on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCHIPOTESIS).toEqual(jasmine.objectContaining({ idHipotesis: 123 }));
      });
    });
  });
});

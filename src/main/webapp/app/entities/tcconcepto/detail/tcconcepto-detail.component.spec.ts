import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCCONCEPTODetailComponent } from './tcconcepto-detail.component';

describe('Component Tests', () => {
  describe('TCCONCEPTO Management Detail Component', () => {
    let comp: TCCONCEPTODetailComponent;
    let fixture: ComponentFixture<TCCONCEPTODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCCONCEPTODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCCONCEPTO: { idConcepto: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCCONCEPTODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCCONCEPTODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCCONCEPTO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCCONCEPTO).toEqual(jasmine.objectContaining({ idConcepto: 123 }));
      });
    });
  });
});

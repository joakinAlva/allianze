import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCCOBERTURADetailComponent } from './tccobertura-detail.component';

describe('Component Tests', () => {
  describe('TCCOBERTURA Management Detail Component', () => {
    let comp: TCCOBERTURADetailComponent;
    let fixture: ComponentFixture<TCCOBERTURADetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCCOBERTURADetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCCOBERTURA: { idCobertura: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCCOBERTURADetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCCOBERTURADetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCCOBERTURA on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCCOBERTURA).toEqual(jasmine.objectContaining({ idCobertura: 123 }));
      });
    });
  });
});

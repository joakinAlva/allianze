import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCREFENCIADetailComponent } from './tcrefencia-detail.component';

describe('Component Tests', () => {
  describe('TCREFENCIA Management Detail Component', () => {
    let comp: TCREFENCIADetailComponent;
    let fixture: ComponentFixture<TCREFENCIADetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCREFENCIADetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCREFENCIA: { idReferencia: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCREFENCIADetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCREFENCIADetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCREFENCIA on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCREFENCIA).toEqual(jasmine.objectContaining({ idReferencia: 123 }));
      });
    });
  });
});

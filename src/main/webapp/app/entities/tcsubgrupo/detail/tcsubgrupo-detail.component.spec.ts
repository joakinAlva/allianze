import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCSUBGRUPODetailComponent } from './tcsubgrupo-detail.component';

describe('Component Tests', () => {
  describe('TCSUBGRUPO Management Detail Component', () => {
    let comp: TCSUBGRUPODetailComponent;
    let fixture: ComponentFixture<TCSUBGRUPODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCSUBGRUPODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCSUBGRUPO: { idSubGrupo: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCSUBGRUPODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCSUBGRUPODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCSUBGRUPO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCSUBGRUPO).toEqual(jasmine.objectContaining({ idSubGrupo: 123 }));
      });
    });
  });
});

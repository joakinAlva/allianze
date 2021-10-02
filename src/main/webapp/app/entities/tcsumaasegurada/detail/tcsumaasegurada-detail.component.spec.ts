import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCSUMAASEGURADADetailComponent } from './tcsumaasegurada-detail.component';

describe('Component Tests', () => {
  describe('TCSUMAASEGURADA Management Detail Component', () => {
    let comp: TCSUMAASEGURADADetailComponent;
    let fixture: ComponentFixture<TCSUMAASEGURADADetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCSUMAASEGURADADetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCSUMAASEGURADA: { idSumaAsegurada: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCSUMAASEGURADADetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCSUMAASEGURADADetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCSUMAASEGURADA on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCSUMAASEGURADA).toEqual(jasmine.objectContaining({ idSumaAsegurada: 123 }));
      });
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCDESCUENTOSUMAASEGURADADetailComponent } from './tcdescuentosumaasegurada-detail.component';

describe('Component Tests', () => {
  describe('TCDESCUENTOSUMAASEGURADA Management Detail Component', () => {
    let comp: TCDESCUENTOSUMAASEGURADADetailComponent;
    let fixture: ComponentFixture<TCDESCUENTOSUMAASEGURADADetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCDESCUENTOSUMAASEGURADADetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCDESCUENTOSUMAASEGURADA: { idDescuentoSumaAsegurada: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCDESCUENTOSUMAASEGURADADetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCDESCUENTOSUMAASEGURADADetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCDESCUENTOSUMAASEGURADA on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCDESCUENTOSUMAASEGURADA).toEqual(jasmine.objectContaining({ idDescuentoSumaAsegurada: 123 }));
      });
    });
  });
});

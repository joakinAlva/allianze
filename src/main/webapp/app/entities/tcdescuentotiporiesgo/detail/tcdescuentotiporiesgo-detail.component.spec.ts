import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCDESCUENTOTIPORIESGODetailComponent } from './tcdescuentotiporiesgo-detail.component';

describe('Component Tests', () => {
  describe('TCDESCUENTOTIPORIESGO Management Detail Component', () => {
    let comp: TCDESCUENTOTIPORIESGODetailComponent;
    let fixture: ComponentFixture<TCDESCUENTOTIPORIESGODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCDESCUENTOTIPORIESGODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCDESCUENTOTIPORIESGO: { idDescuentoTipoRiesgo: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCDESCUENTOTIPORIESGODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCDESCUENTOTIPORIESGODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCDESCUENTOTIPORIESGO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCDESCUENTOTIPORIESGO).toEqual(jasmine.objectContaining({ idDescuentoTipoRiesgo: 123 }));
      });
    });
  });
});

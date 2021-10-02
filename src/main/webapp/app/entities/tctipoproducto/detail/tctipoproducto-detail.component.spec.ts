import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCTIPOPRODUCTODetailComponent } from './tctipoproducto-detail.component';

describe('Component Tests', () => {
  describe('TCTIPOPRODUCTO Management Detail Component', () => {
    let comp: TCTIPOPRODUCTODetailComponent;
    let fixture: ComponentFixture<TCTIPOPRODUCTODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCTIPOPRODUCTODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCTIPOPRODUCTO: { idTipoProducto: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCTIPOPRODUCTODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCTIPOPRODUCTODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCTIPOPRODUCTO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCTIPOPRODUCTO).toEqual(jasmine.objectContaining({ idTipoProducto: 123 }));
      });
    });
  });
});

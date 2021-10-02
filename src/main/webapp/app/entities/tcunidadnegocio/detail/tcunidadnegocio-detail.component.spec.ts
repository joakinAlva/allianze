import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCUNIDADNEGOCIODetailComponent } from './tcunidadnegocio-detail.component';

describe('Component Tests', () => {
  describe('TCUNIDADNEGOCIO Management Detail Component', () => {
    let comp: TCUNIDADNEGOCIODetailComponent;
    let fixture: ComponentFixture<TCUNIDADNEGOCIODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCUNIDADNEGOCIODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCUNIDADNEGOCIO: { idUnidadNegocio: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCUNIDADNEGOCIODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCUNIDADNEGOCIODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCUNIDADNEGOCIO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCUNIDADNEGOCIO).toEqual(jasmine.objectContaining({ idUnidadNegocio: 123 }));
      });
    });
  });
});

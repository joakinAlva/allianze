import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCPERFILDetailComponent } from './tcperfil-detail.component';

describe('Component Tests', () => {
  describe('TCPERFIL Management Detail Component', () => {
    let comp: TCPERFILDetailComponent;
    let fixture: ComponentFixture<TCPERFILDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCPERFILDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCPERFIL: { idPerfil: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCPERFILDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCPERFILDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCPERFIL on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCPERFIL).toEqual(jasmine.objectContaining({ idPerfil: 123 }));
      });
    });
  });
});

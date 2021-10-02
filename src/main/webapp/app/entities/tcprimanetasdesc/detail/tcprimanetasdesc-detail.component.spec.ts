import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCPRIMANETASDESCDetailComponent } from './tcprimanetasdesc-detail.component';

describe('Component Tests', () => {
  describe('TCPRIMANETASDESC Management Detail Component', () => {
    let comp: TCPRIMANETASDESCDetailComponent;
    let fixture: ComponentFixture<TCPRIMANETASDESCDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCPRIMANETASDESCDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCPRIMANETASDESC: { idPrimaNetaSdesc: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCPRIMANETASDESCDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCPRIMANETASDESCDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCPRIMANETASDESC on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCPRIMANETASDESC).toEqual(jasmine.objectContaining({ idPrimaNetaSdesc: 123 }));
      });
    });
  });
});

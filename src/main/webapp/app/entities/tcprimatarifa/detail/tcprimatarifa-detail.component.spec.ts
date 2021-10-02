import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCPRIMATARIFADetailComponent } from './tcprimatarifa-detail.component';

describe('Component Tests', () => {
  describe('TCPRIMATARIFA Management Detail Component', () => {
    let comp: TCPRIMATARIFADetailComponent;
    let fixture: ComponentFixture<TCPRIMATARIFADetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCPRIMATARIFADetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCPRIMATARIFA: { idPrimaTarifa: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCPRIMATARIFADetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCPRIMATARIFADetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCPRIMATARIFA on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCPRIMATARIFA).toEqual(jasmine.objectContaining({ idPrimaTarifa: 123 }));
      });
    });
  });
});

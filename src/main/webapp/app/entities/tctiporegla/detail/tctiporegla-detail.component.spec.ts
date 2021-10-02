import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TCTIPOREGLADetailComponent } from './tctiporegla-detail.component';

describe('Component Tests', () => {
  describe('TCTIPOREGLA Management Detail Component', () => {
    let comp: TCTIPOREGLADetailComponent;
    let fixture: ComponentFixture<TCTIPOREGLADetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TCTIPOREGLADetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tCTIPOREGLA: { idTipoRegla: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TCTIPOREGLADetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCTIPOREGLADetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tCTIPOREGLA on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tCTIPOREGLA).toEqual(jasmine.objectContaining({ idTipoRegla: 123 }));
      });
    });
  });
});

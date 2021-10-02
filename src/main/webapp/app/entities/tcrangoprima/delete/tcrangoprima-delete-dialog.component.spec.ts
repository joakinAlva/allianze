jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { TCRANGOPRIMAService } from '../service/tcrangoprima.service';

import { TCRANGOPRIMADeleteDialogComponent } from './tcrangoprima-delete-dialog.component';

describe('Component Tests', () => {
  describe('TCRANGOPRIMA Management Delete Component', () => {
    let comp: TCRANGOPRIMADeleteDialogComponent;
    let fixture: ComponentFixture<TCRANGOPRIMADeleteDialogComponent>;
    let service: TCRANGOPRIMAService;
    let mockActiveModal: NgbActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCRANGOPRIMADeleteDialogComponent],
        providers: [NgbActiveModal],
      })
        .overrideTemplate(TCRANGOPRIMADeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCRANGOPRIMADeleteDialogComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCRANGOPRIMAService);
      mockActiveModal = TestBed.inject(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.close).not.toHaveBeenCalled();
        expect(mockActiveModal.dismiss).toHaveBeenCalled();
      });
    });
  });
});

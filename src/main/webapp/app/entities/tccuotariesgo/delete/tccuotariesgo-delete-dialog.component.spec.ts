jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { TCCUOTARIESGOService } from '../service/tccuotariesgo.service';

import { TCCUOTARIESGODeleteDialogComponent } from './tccuotariesgo-delete-dialog.component';

describe('Component Tests', () => {
  describe('TCCUOTARIESGO Management Delete Component', () => {
    let comp: TCCUOTARIESGODeleteDialogComponent;
    let fixture: ComponentFixture<TCCUOTARIESGODeleteDialogComponent>;
    let service: TCCUOTARIESGOService;
    let mockActiveModal: NgbActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCUOTARIESGODeleteDialogComponent],
        providers: [NgbActiveModal],
      })
        .overrideTemplate(TCCUOTARIESGODeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCCUOTARIESGODeleteDialogComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCCUOTARIESGOService);
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

jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { TCFACTORDESCUENTOService } from '../service/tcfactordescuento.service';

import { TCFACTORDESCUENTODeleteDialogComponent } from './tcfactordescuento-delete-dialog.component';

describe('Component Tests', () => {
  describe('TCFACTORDESCUENTO Management Delete Component', () => {
    let comp: TCFACTORDESCUENTODeleteDialogComponent;
    let fixture: ComponentFixture<TCFACTORDESCUENTODeleteDialogComponent>;
    let service: TCFACTORDESCUENTOService;
    let mockActiveModal: NgbActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCFACTORDESCUENTODeleteDialogComponent],
        providers: [NgbActiveModal],
      })
        .overrideTemplate(TCFACTORDESCUENTODeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCFACTORDESCUENTODeleteDialogComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCFACTORDESCUENTOService);
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

jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { TCUNIDADNEGOCIOService } from '../service/tcunidadnegocio.service';

import { TCUNIDADNEGOCIODeleteDialogComponent } from './tcunidadnegocio-delete-dialog.component';

describe('Component Tests', () => {
  describe('TCUNIDADNEGOCIO Management Delete Component', () => {
    let comp: TCUNIDADNEGOCIODeleteDialogComponent;
    let fixture: ComponentFixture<TCUNIDADNEGOCIODeleteDialogComponent>;
    let service: TCUNIDADNEGOCIOService;
    let mockActiveModal: NgbActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCUNIDADNEGOCIODeleteDialogComponent],
        providers: [NgbActiveModal],
      })
        .overrideTemplate(TCUNIDADNEGOCIODeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCUNIDADNEGOCIODeleteDialogComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCUNIDADNEGOCIOService);
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

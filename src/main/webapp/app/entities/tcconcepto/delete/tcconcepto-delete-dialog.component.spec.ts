jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { TCCONCEPTOService } from '../service/tcconcepto.service';

import { TCCONCEPTODeleteDialogComponent } from './tcconcepto-delete-dialog.component';

describe('Component Tests', () => {
  describe('TCCONCEPTO Management Delete Component', () => {
    let comp: TCCONCEPTODeleteDialogComponent;
    let fixture: ComponentFixture<TCCONCEPTODeleteDialogComponent>;
    let service: TCCONCEPTOService;
    let mockActiveModal: NgbActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCONCEPTODeleteDialogComponent],
        providers: [NgbActiveModal],
      })
        .overrideTemplate(TCCONCEPTODeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCCONCEPTODeleteDialogComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCCONCEPTOService);
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

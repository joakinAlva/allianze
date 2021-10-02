jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { TCCOBERTURAService } from '../service/tccobertura.service';

import { TCCOBERTURADeleteDialogComponent } from './tccobertura-delete-dialog.component';

describe('Component Tests', () => {
  describe('TCCOBERTURA Management Delete Component', () => {
    let comp: TCCOBERTURADeleteDialogComponent;
    let fixture: ComponentFixture<TCCOBERTURADeleteDialogComponent>;
    let service: TCCOBERTURAService;
    let mockActiveModal: NgbActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCOBERTURADeleteDialogComponent],
        providers: [NgbActiveModal],
      })
        .overrideTemplate(TCCOBERTURADeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCCOBERTURADeleteDialogComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCCOBERTURAService);
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

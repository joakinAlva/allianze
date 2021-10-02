jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { TCREFENCIAService } from '../service/tcrefencia.service';

import { TCREFENCIADeleteDialogComponent } from './tcrefencia-delete-dialog.component';

describe('Component Tests', () => {
  describe('TCREFENCIA Management Delete Component', () => {
    let comp: TCREFENCIADeleteDialogComponent;
    let fixture: ComponentFixture<TCREFENCIADeleteDialogComponent>;
    let service: TCREFENCIAService;
    let mockActiveModal: NgbActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCREFENCIADeleteDialogComponent],
        providers: [NgbActiveModal],
      })
        .overrideTemplate(TCREFENCIADeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TCREFENCIADeleteDialogComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(TCREFENCIAService);
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

jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCDESCUENTOSUMAASEGURADAService } from '../service/tcdescuentosumaasegurada.service';
import { ITCDESCUENTOSUMAASEGURADA, TCDESCUENTOSUMAASEGURADA } from '../tcdescuentosumaasegurada.model';

import { TCDESCUENTOSUMAASEGURADAUpdateComponent } from './tcdescuentosumaasegurada-update.component';

describe('Component Tests', () => {
  describe('TCDESCUENTOSUMAASEGURADA Management Update Component', () => {
    let comp: TCDESCUENTOSUMAASEGURADAUpdateComponent;
    let fixture: ComponentFixture<TCDESCUENTOSUMAASEGURADAUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCDESCUENTOSUMAASEGURADAService: TCDESCUENTOSUMAASEGURADAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCDESCUENTOSUMAASEGURADAUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCDESCUENTOSUMAASEGURADAUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCDESCUENTOSUMAASEGURADAUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCDESCUENTOSUMAASEGURADAService = TestBed.inject(TCDESCUENTOSUMAASEGURADAService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCDESCUENTOSUMAASEGURADA: ITCDESCUENTOSUMAASEGURADA = { idDescuentoSumaAsegurada: 456 };

        activatedRoute.data = of({ tCDESCUENTOSUMAASEGURADA });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCDESCUENTOSUMAASEGURADA));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCDESCUENTOSUMAASEGURADA = { idDescuentoSumaAsegurada: 123 };
        spyOn(tCDESCUENTOSUMAASEGURADAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCDESCUENTOSUMAASEGURADA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCDESCUENTOSUMAASEGURADA }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCDESCUENTOSUMAASEGURADAService.update).toHaveBeenCalledWith(tCDESCUENTOSUMAASEGURADA);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCDESCUENTOSUMAASEGURADA = new TCDESCUENTOSUMAASEGURADA();
        spyOn(tCDESCUENTOSUMAASEGURADAService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCDESCUENTOSUMAASEGURADA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCDESCUENTOSUMAASEGURADA }));
        saveSubject.complete();

        // THEN
        expect(tCDESCUENTOSUMAASEGURADAService.create).toHaveBeenCalledWith(tCDESCUENTOSUMAASEGURADA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCDESCUENTOSUMAASEGURADA = { idDescuentoSumaAsegurada: 123 };
        spyOn(tCDESCUENTOSUMAASEGURADAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCDESCUENTOSUMAASEGURADA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCDESCUENTOSUMAASEGURADAService.update).toHaveBeenCalledWith(tCDESCUENTOSUMAASEGURADA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});

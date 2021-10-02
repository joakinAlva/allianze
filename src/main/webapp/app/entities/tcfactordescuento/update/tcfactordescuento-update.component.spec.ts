jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCFACTORDESCUENTOService } from '../service/tcfactordescuento.service';
import { ITCFACTORDESCUENTO, TCFACTORDESCUENTO } from '../tcfactordescuento.model';

import { TCFACTORDESCUENTOUpdateComponent } from './tcfactordescuento-update.component';

describe('Component Tests', () => {
  describe('TCFACTORDESCUENTO Management Update Component', () => {
    let comp: TCFACTORDESCUENTOUpdateComponent;
    let fixture: ComponentFixture<TCFACTORDESCUENTOUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCFACTORDESCUENTOService: TCFACTORDESCUENTOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCFACTORDESCUENTOUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCFACTORDESCUENTOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCFACTORDESCUENTOUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCFACTORDESCUENTOService = TestBed.inject(TCFACTORDESCUENTOService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCFACTORDESCUENTO: ITCFACTORDESCUENTO = { idFactorDescuento: 456 };

        activatedRoute.data = of({ tCFACTORDESCUENTO });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCFACTORDESCUENTO));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCFACTORDESCUENTO = { idFactorDescuento: 123 };
        spyOn(tCFACTORDESCUENTOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCFACTORDESCUENTO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCFACTORDESCUENTO }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCFACTORDESCUENTOService.update).toHaveBeenCalledWith(tCFACTORDESCUENTO);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCFACTORDESCUENTO = new TCFACTORDESCUENTO();
        spyOn(tCFACTORDESCUENTOService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCFACTORDESCUENTO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCFACTORDESCUENTO }));
        saveSubject.complete();

        // THEN
        expect(tCFACTORDESCUENTOService.create).toHaveBeenCalledWith(tCFACTORDESCUENTO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCFACTORDESCUENTO = { idFactorDescuento: 123 };
        spyOn(tCFACTORDESCUENTOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCFACTORDESCUENTO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCFACTORDESCUENTOService.update).toHaveBeenCalledWith(tCFACTORDESCUENTO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});

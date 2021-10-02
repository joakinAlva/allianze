jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCCUOTATARIFASDESCService } from '../service/tccuotatarifasdesc.service';
import { ITCCUOTATARIFASDESC, TCCUOTATARIFASDESC } from '../tccuotatarifasdesc.model';

import { TCCUOTATARIFASDESCUpdateComponent } from './tccuotatarifasdesc-update.component';

describe('Component Tests', () => {
  describe('TCCUOTATARIFASDESC Management Update Component', () => {
    let comp: TCCUOTATARIFASDESCUpdateComponent;
    let fixture: ComponentFixture<TCCUOTATARIFASDESCUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCCUOTATARIFASDESCService: TCCUOTATARIFASDESCService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCUOTATARIFASDESCUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCCUOTATARIFASDESCUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCUOTATARIFASDESCUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCCUOTATARIFASDESCService = TestBed.inject(TCCUOTATARIFASDESCService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCCUOTATARIFASDESC: ITCCUOTATARIFASDESC = { idCuotaTarifaSdesc: 456 };

        activatedRoute.data = of({ tCCUOTATARIFASDESC });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCCUOTATARIFASDESC));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCUOTATARIFASDESC = { idCuotaTarifaSdesc: 123 };
        spyOn(tCCUOTATARIFASDESCService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCUOTATARIFASDESC });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCUOTATARIFASDESC }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCCUOTATARIFASDESCService.update).toHaveBeenCalledWith(tCCUOTATARIFASDESC);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCUOTATARIFASDESC = new TCCUOTATARIFASDESC();
        spyOn(tCCUOTATARIFASDESCService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCUOTATARIFASDESC });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCUOTATARIFASDESC }));
        saveSubject.complete();

        // THEN
        expect(tCCUOTATARIFASDESCService.create).toHaveBeenCalledWith(tCCUOTATARIFASDESC);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCUOTATARIFASDESC = { idCuotaTarifaSdesc: 123 };
        spyOn(tCCUOTATARIFASDESCService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCUOTATARIFASDESC });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCCUOTATARIFASDESCService.update).toHaveBeenCalledWith(tCCUOTATARIFASDESC);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});

jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCCUOTARIESGOService } from '../service/tccuotariesgo.service';
import { ITCCUOTARIESGO, TCCUOTARIESGO } from '../tccuotariesgo.model';

import { TCCUOTARIESGOUpdateComponent } from './tccuotariesgo-update.component';

describe('Component Tests', () => {
  describe('TCCUOTARIESGO Management Update Component', () => {
    let comp: TCCUOTARIESGOUpdateComponent;
    let fixture: ComponentFixture<TCCUOTARIESGOUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCCUOTARIESGOService: TCCUOTARIESGOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCUOTARIESGOUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCCUOTARIESGOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCUOTARIESGOUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCCUOTARIESGOService = TestBed.inject(TCCUOTARIESGOService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCCUOTARIESGO: ITCCUOTARIESGO = { idCuotaRiesgo: 456 };

        activatedRoute.data = of({ tCCUOTARIESGO });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCCUOTARIESGO));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCUOTARIESGO = { idCuotaRiesgo: 123 };
        spyOn(tCCUOTARIESGOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCUOTARIESGO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCUOTARIESGO }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCCUOTARIESGOService.update).toHaveBeenCalledWith(tCCUOTARIESGO);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCUOTARIESGO = new TCCUOTARIESGO();
        spyOn(tCCUOTARIESGOService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCUOTARIESGO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCUOTARIESGO }));
        saveSubject.complete();

        // THEN
        expect(tCCUOTARIESGOService.create).toHaveBeenCalledWith(tCCUOTARIESGO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCUOTARIESGO = { idCuotaRiesgo: 123 };
        spyOn(tCCUOTARIESGOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCUOTARIESGO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCCUOTARIESGOService.update).toHaveBeenCalledWith(tCCUOTARIESGO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});

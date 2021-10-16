jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCCOVIDTARIFASService } from '../service/tccovidtarifas.service';
import { ITCCOVIDTARIFAS, TCCOVIDTARIFAS } from '../tccovidtarifas.model';

import { TCCOVIDTARIFASUpdateComponent } from './tccovidtarifas-update.component';

describe('Component Tests', () => {
  describe('TCCOVIDTARIFAS Management Update Component', () => {
    let comp: TCCOVIDTARIFASUpdateComponent;
    let fixture: ComponentFixture<TCCOVIDTARIFASUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCCOVIDTARIFASService: TCCOVIDTARIFASService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCOVIDTARIFASUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCCOVIDTARIFASUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCOVIDTARIFASUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCCOVIDTARIFASService = TestBed.inject(TCCOVIDTARIFASService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCCOVIDTARIFAS: ITCCOVIDTARIFAS = { idCovidTarifas: 456 };

        activatedRoute.data = of({ tCCOVIDTARIFAS });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCCOVIDTARIFAS));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCOVIDTARIFAS = { idCovidTarifas: 123 };
        spyOn(tCCOVIDTARIFASService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCOVIDTARIFAS });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCOVIDTARIFAS }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCCOVIDTARIFASService.update).toHaveBeenCalledWith(tCCOVIDTARIFAS);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCOVIDTARIFAS = new TCCOVIDTARIFAS();
        spyOn(tCCOVIDTARIFASService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCOVIDTARIFAS });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCOVIDTARIFAS }));
        saveSubject.complete();

        // THEN
        expect(tCCOVIDTARIFASService.create).toHaveBeenCalledWith(tCCOVIDTARIFAS);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCOVIDTARIFAS = { idCovidTarifas: 123 };
        spyOn(tCCOVIDTARIFASService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCOVIDTARIFAS });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCCOVIDTARIFASService.update).toHaveBeenCalledWith(tCCOVIDTARIFAS);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});

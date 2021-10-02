jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCCOEFICIENTEService } from '../service/tccoeficiente.service';
import { ITCCOEFICIENTE, TCCOEFICIENTE } from '../tccoeficiente.model';

import { TCCOEFICIENTEUpdateComponent } from './tccoeficiente-update.component';

describe('Component Tests', () => {
  describe('TCCOEFICIENTE Management Update Component', () => {
    let comp: TCCOEFICIENTEUpdateComponent;
    let fixture: ComponentFixture<TCCOEFICIENTEUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCCOEFICIENTEService: TCCOEFICIENTEService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCOEFICIENTEUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCCOEFICIENTEUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCOEFICIENTEUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCCOEFICIENTEService = TestBed.inject(TCCOEFICIENTEService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCCOEFICIENTE: ITCCOEFICIENTE = { idCoeficiente: 456 };

        activatedRoute.data = of({ tCCOEFICIENTE });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCCOEFICIENTE));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCOEFICIENTE = { idCoeficiente: 123 };
        spyOn(tCCOEFICIENTEService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCOEFICIENTE });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCOEFICIENTE }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCCOEFICIENTEService.update).toHaveBeenCalledWith(tCCOEFICIENTE);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCOEFICIENTE = new TCCOEFICIENTE();
        spyOn(tCCOEFICIENTEService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCOEFICIENTE });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCOEFICIENTE }));
        saveSubject.complete();

        // THEN
        expect(tCCOEFICIENTEService.create).toHaveBeenCalledWith(tCCOEFICIENTE);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCOEFICIENTE = { idCoeficiente: 123 };
        spyOn(tCCOEFICIENTEService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCOEFICIENTE });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCCOEFICIENTEService.update).toHaveBeenCalledWith(tCCOEFICIENTE);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});

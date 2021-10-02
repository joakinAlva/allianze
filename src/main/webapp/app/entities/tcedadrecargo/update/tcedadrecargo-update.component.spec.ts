jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCEDADRECARGOService } from '../service/tcedadrecargo.service';
import { ITCEDADRECARGO, TCEDADRECARGO } from '../tcedadrecargo.model';

import { TCEDADRECARGOUpdateComponent } from './tcedadrecargo-update.component';

describe('Component Tests', () => {
  describe('TCEDADRECARGO Management Update Component', () => {
    let comp: TCEDADRECARGOUpdateComponent;
    let fixture: ComponentFixture<TCEDADRECARGOUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCEDADRECARGOService: TCEDADRECARGOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCEDADRECARGOUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCEDADRECARGOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCEDADRECARGOUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCEDADRECARGOService = TestBed.inject(TCEDADRECARGOService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCEDADRECARGO: ITCEDADRECARGO = { idEdadRecargo: 456 };

        activatedRoute.data = of({ tCEDADRECARGO });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCEDADRECARGO));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCEDADRECARGO = { idEdadRecargo: 123 };
        spyOn(tCEDADRECARGOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCEDADRECARGO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCEDADRECARGO }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCEDADRECARGOService.update).toHaveBeenCalledWith(tCEDADRECARGO);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCEDADRECARGO = new TCEDADRECARGO();
        spyOn(tCEDADRECARGOService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCEDADRECARGO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCEDADRECARGO }));
        saveSubject.complete();

        // THEN
        expect(tCEDADRECARGOService.create).toHaveBeenCalledWith(tCEDADRECARGO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCEDADRECARGO = { idEdadRecargo: 123 };
        spyOn(tCEDADRECARGOService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCEDADRECARGO });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCEDADRECARGOService.update).toHaveBeenCalledWith(tCEDADRECARGO);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});

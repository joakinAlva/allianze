jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCCOBERTURAService } from '../service/tccobertura.service';
import { ITCCOBERTURA, TCCOBERTURA } from '../tccobertura.model';

import { TCCOBERTURAUpdateComponent } from './tccobertura-update.component';

describe('Component Tests', () => {
  describe('TCCOBERTURA Management Update Component', () => {
    let comp: TCCOBERTURAUpdateComponent;
    let fixture: ComponentFixture<TCCOBERTURAUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCCOBERTURAService: TCCOBERTURAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCCOBERTURAUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCCOBERTURAUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCCOBERTURAUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCCOBERTURAService = TestBed.inject(TCCOBERTURAService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCCOBERTURA: ITCCOBERTURA = { idCobertura: 456 };

        activatedRoute.data = of({ tCCOBERTURA });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCCOBERTURA));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCOBERTURA = { idCobertura: 123 };
        spyOn(tCCOBERTURAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCOBERTURA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCOBERTURA }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCCOBERTURAService.update).toHaveBeenCalledWith(tCCOBERTURA);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCOBERTURA = new TCCOBERTURA();
        spyOn(tCCOBERTURAService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCOBERTURA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCCOBERTURA }));
        saveSubject.complete();

        // THEN
        expect(tCCOBERTURAService.create).toHaveBeenCalledWith(tCCOBERTURA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCCOBERTURA = { idCobertura: 123 };
        spyOn(tCCOBERTURAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCCOBERTURA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCCOBERTURAService.update).toHaveBeenCalledWith(tCCOBERTURA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});

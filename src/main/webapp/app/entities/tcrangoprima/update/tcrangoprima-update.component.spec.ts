jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCRANGOPRIMAService } from '../service/tcrangoprima.service';
import { ITCRANGOPRIMA, TCRANGOPRIMA } from '../tcrangoprima.model';

import { TCRANGOPRIMAUpdateComponent } from './tcrangoprima-update.component';

describe('Component Tests', () => {
  describe('TCRANGOPRIMA Management Update Component', () => {
    let comp: TCRANGOPRIMAUpdateComponent;
    let fixture: ComponentFixture<TCRANGOPRIMAUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCRANGOPRIMAService: TCRANGOPRIMAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCRANGOPRIMAUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCRANGOPRIMAUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCRANGOPRIMAUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCRANGOPRIMAService = TestBed.inject(TCRANGOPRIMAService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCRANGOPRIMA: ITCRANGOPRIMA = { idRangoPrima: 456 };

        activatedRoute.data = of({ tCRANGOPRIMA });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCRANGOPRIMA));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCRANGOPRIMA = { idRangoPrima: 123 };
        spyOn(tCRANGOPRIMAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCRANGOPRIMA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCRANGOPRIMA }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCRANGOPRIMAService.update).toHaveBeenCalledWith(tCRANGOPRIMA);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCRANGOPRIMA = new TCRANGOPRIMA();
        spyOn(tCRANGOPRIMAService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCRANGOPRIMA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCRANGOPRIMA }));
        saveSubject.complete();

        // THEN
        expect(tCRANGOPRIMAService.create).toHaveBeenCalledWith(tCRANGOPRIMA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCRANGOPRIMA = { idRangoPrima: 123 };
        spyOn(tCRANGOPRIMAService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCRANGOPRIMA });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCRANGOPRIMAService.update).toHaveBeenCalledWith(tCRANGOPRIMA);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});

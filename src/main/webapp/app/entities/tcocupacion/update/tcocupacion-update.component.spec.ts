jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TCOCUPACIONService } from '../service/tcocupacion.service';
import { ITCOCUPACION, TCOCUPACION } from '../tcocupacion.model';

import { TCOCUPACIONUpdateComponent } from './tcocupacion-update.component';

describe('Component Tests', () => {
  describe('TCOCUPACION Management Update Component', () => {
    let comp: TCOCUPACIONUpdateComponent;
    let fixture: ComponentFixture<TCOCUPACIONUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tCOCUPACIONService: TCOCUPACIONService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TCOCUPACIONUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TCOCUPACIONUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TCOCUPACIONUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tCOCUPACIONService = TestBed.inject(TCOCUPACIONService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tCOCUPACION: ITCOCUPACION = { idOcupacion: 456 };

        activatedRoute.data = of({ tCOCUPACION });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tCOCUPACION));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCOCUPACION = { idOcupacion: 123 };
        spyOn(tCOCUPACIONService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCOCUPACION });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCOCUPACION }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tCOCUPACIONService.update).toHaveBeenCalledWith(tCOCUPACION);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCOCUPACION = new TCOCUPACION();
        spyOn(tCOCUPACIONService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCOCUPACION });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tCOCUPACION }));
        saveSubject.complete();

        // THEN
        expect(tCOCUPACIONService.create).toHaveBeenCalledWith(tCOCUPACION);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tCOCUPACION = { idOcupacion: 123 };
        spyOn(tCOCUPACIONService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tCOCUPACION });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tCOCUPACIONService.update).toHaveBeenCalledWith(tCOCUPACION);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});

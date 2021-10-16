jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { TMCOTIZACIONService } from '../service/tmcotizacion.service';
import { ITMCOTIZACION, TMCOTIZACION } from '../tmcotizacion.model';

import { TMCOTIZACIONUpdateComponent } from './tmcotizacion-update.component';

describe('Component Tests', () => {
  describe('TMCOTIZACION Management Update Component', () => {
    let comp: TMCOTIZACIONUpdateComponent;
    let fixture: ComponentFixture<TMCOTIZACIONUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tMCOTIZACIONService: TMCOTIZACIONService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TMCOTIZACIONUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TMCOTIZACIONUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TMCOTIZACIONUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tMCOTIZACIONService = TestBed.inject(TMCOTIZACIONService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const tMCOTIZACION: ITMCOTIZACION = { idCotizacion: 456 };

        activatedRoute.data = of({ tMCOTIZACION });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(tMCOTIZACION));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tMCOTIZACION = { idCotizacion: 123 };
        spyOn(tMCOTIZACIONService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tMCOTIZACION });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tMCOTIZACION }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tMCOTIZACIONService.update).toHaveBeenCalledWith(tMCOTIZACION);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tMCOTIZACION = new TMCOTIZACION();
        spyOn(tMCOTIZACIONService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tMCOTIZACION });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tMCOTIZACION }));
        saveSubject.complete();

        // THEN
        expect(tMCOTIZACIONService.create).toHaveBeenCalledWith(tMCOTIZACION);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const tMCOTIZACION = { idCotizacion: 123 };
        spyOn(tMCOTIZACIONService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ tMCOTIZACION });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(tMCOTIZACIONService.update).toHaveBeenCalledWith(tMCOTIZACION);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
